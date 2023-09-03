package com.forstuad.bankserver.service;


import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.domain.CashFlow;
import com.forstuad.bankserver.repository.AccountRepository;
import com.forstuad.bankserver.repository.CashFlowRepository;
import com.forstuad.bankserver.util.AccountUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService{

    private final AccountRepository accountRepository;
    private final CashFlowRepository cashFlowRepository;
    private final AccountUtilService accountUtilService;

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    @Override
    public Account findByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    @Override
    public void deposit(Long userId, int balance) {
        Account userAccount = accountRepository.findByUserId(userId);
        userAccount.setBalance(userAccount.getBalance() + balance);
        accountRepository.save(userAccount);
    }

    @Override
    public Account createAccount(Long userId) {
        Account account = Account
                .builder()
                .accountId(accountUtilService.createAccountId())
                .userId(userId)
                .build();

        accountRepository.save(account);
        return account;

    }

    @Override
    public void transferAccount(String sender, String receiver, int amount) {

        Account senderAccount = accountRepository.findByAccountId(sender);
        Account receiverAccount = accountRepository.findByAccountId(receiver);

        if (senderAccount == null || receiverAccount == null) {
            throw new IllegalArgumentException("sender or receiver 가 없습니다.");
        }

        if (senderAccount.getBalance() < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        CashFlow cashFlow = CashFlow.builder()
                .sender(sender)
                .receiver(receiver)
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .build();

        //변경사항 저장
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        //계좌 이체내역 저장
        cashFlowRepository.save(cashFlow);

    }

}
