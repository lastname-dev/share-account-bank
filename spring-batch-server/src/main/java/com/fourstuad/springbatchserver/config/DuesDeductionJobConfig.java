package com.fourstuad.springbatchserver.config;


import com.fourstuad.springbatchserver.domain.Account;
import com.fourstuad.springbatchserver.domain.DuesTransaction;
import com.fourstuad.springbatchserver.repository.AccountRepository;
import com.fourstuad.springbatchserver.repository.DuesTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class DuesDeductionJobConfig {

    private final AccountRepository accountRepository;
    private final DuesTransactionRepository duesTransactionRepository;
    // Spring Batch Job 정의
    @Bean
    public Job duesDeductionJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("duesDeductionJob",jobRepository)
                .start(step)
                .build();
    }

    // Spring Batch Step 정의
    @Bean
    public Step duesDeductionStep(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new StepBuilder("duesDeductionStep",jobRepository)
                .chunk(10,transactionManager)
                .reader(accountReader())
                .transactionManager(transactionManager)
                .writer(duesTransactionWriter())
                .build();
    }

    // 계정을 읽어오는 ItemReader를 구현해야 합니다.
    @Bean
    public ItemReader<Account> accountReader() {
        List<Account> allAcountList = accountRepository.findAll();
        List<Account> duesDeductionLists = new ArrayList<>();
        int todayDate = LocalDateTime.now().getDayOfMonth();

        for(Account account : allAcountList){
            if(account.isRepresentedAccount() && !account.isSettlementStatus()){
                if(account.getDuesDate()!=todayDate)continue;
                long groupId = account.getGroupId();
                List<Account> allByGroupId = accountRepository.findAllByGroupId(groupId);
                duesDeductionLists.addAll(allByGroupId);
            }

        }
        return new IteratorItemReader<>(duesDeductionLists);
    }

    // 계정을 처리하는 ItemProcessor를 구현해야 합니다.
    @Bean
    public ItemProcessor<Account, DuesTransaction> accountProcessor() {
        return account -> {
            // 계정에서 차감할 회비를 계산합니다.
            int dues = account.getDues();

            // 트랜잭션을 기록할 DuesTransaction 객체를 생성합니다.
            DuesTransaction duesTransaction = new DuesTransaction();
            duesTransaction.setGroupId(account.getGroupId());
            duesTransaction.setTransactionDate(LocalDateTime.now());
            // 계정 잔액에서 회비를 차감합니다.
            duesTransaction.setAmount(account.getBalance() - dues);

            // DuesTransaction과 업데이트된 Account를 저장합니다.
            duesTransactionRepository.save(duesTransaction);
            accountRepository.save(account);

            return duesTransaction;
        };
    }

    // DuesTransaction을 데이터베이스에 저장하는 ItemWriter를 구현해야 합니다.
    @Bean
    public ItemWriter<? super Object> duesTransactionWriter() {
        return items -> {
            for (Object duesTransaction : items) {
                if(duesTransaction instanceof  DuesTransaction){
                    DuesTransaction dues = (DuesTransaction)duesTransaction;
                    duesTransactionRepository.save(dues);
                }
            }
        };
    }
}
