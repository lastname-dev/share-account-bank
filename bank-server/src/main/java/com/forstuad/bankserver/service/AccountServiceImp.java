package com.forstuad.bankserver.service;


import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.domain.CashFlow;
import com.forstuad.bankserver.dto.CashFlowHistory;
import com.forstuad.bankserver.repository.AccountRepository;
import com.forstuad.bankserver.repository.CashFlowRepository;
import com.forstuad.bankserver.util.AccountUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public Account findByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId);
    }

    @Override
    public void deposit(String accountId, int balance) {
        Account userAccount = accountRepository.findByAccountId(accountId);
        userAccount.setBalance(userAccount.getBalance() + balance);
        accountRepository.save(userAccount);
    }

    @Override
    public Account createAccount(String userName) {
        Account account = Account
                .builder()
                .accountId(accountUtilService.createAccountId())
                .userName(userName)
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
        if(sender.equals(receiver)){
            throw new IllegalArgumentException("자기 자신한테 보낼 수 없습니다.");
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

    @Override
    public List<CashFlowHistory> getCashFlowList(String accountId) {
        List<CashFlow> cashFlows = cashFlowRepository.findCashFlowsByAccountId(accountId);
        List<CashFlowHistory> cashFlowHistories = new ArrayList<>();

        for(CashFlow cashFlow : cashFlows){

            CashFlowHistory cashFlowHistory = new CashFlowHistory();
            cashFlowHistory.setTime(cashFlow.getDateTime());
            cashFlowHistory.setAmount(cashFlow.getAmount());
            cashFlowHistory.setSender(cashFlow.getSender());
            cashFlowHistory.setReceiver(cashFlow.getReceiver());


            if(accountId.equals(cashFlow.getSender())){
                cashFlowHistory.setType("send");
            }else{
                cashFlowHistory.setType("received");
            }

            cashFlowHistories.add(cashFlowHistory);
        }
        return cashFlowHistories;
    }

    @Override
    public List<Account> findAllByUserName(String userName) {
        return accountRepository.findAllByUserName(userName);
    }

    @Override
    public void disableGroupAccount(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        account.setGroupId(0);
        account.setGroup(false);
        accountRepository.save(account);
    }

    @Override
    public void assignGroupAccount(String accountId,Long groupId) {
        Account account = accountRepository.findByAccountId(accountId);
        account.setGroup(true);
        account.setGroupId(groupId);
        accountRepository.save(account);
    }

    @Override
    public List<Account> findGroupAccountByGroupIds(List<Long> groupIds) {
        return accountRepository.findByGroupIdIn(groupIds);
    }

    @Override
    public List<Account> findAccountByGroupId(Long groupId) {
        return accountRepository.findAllByGroupId(groupId);
    }

    @Override
    public List<String> getAccountsNames(List<Account> accountList) {
        List<String> accountNames = new ArrayList<>();
        for(Account account : accountList){
            accountNames.add(account.getUserName());
        }
        return accountNames;
    }

    @Override
    public Account findRepresentAccount(Long groupId) {
        List<Account> accounts = accountRepository.findAllByGroupId(groupId);
        if (accounts.isEmpty()) {
            // 여기에 원하는 예외를 던집니다.
            throw new NoSuchElementException("Representative account not found for groupId: " + groupId);
        }
        return accounts.get(0);
    }

    @Override
    public void caculateAccountByN(Account representationAccount,List<Account> representedAccounts,int balance) {
        int n = representedAccounts.size();
        int settlementCash = balance / n;
        //대표계좌 0원 설정
        representationAccount.setBalance(0);
        accountRepository.save(representationAccount);
        //정산하기
        for(Account account : representedAccounts){
            account.setBalance(account.getBalance() + settlementCash);
            accountRepository.save(account);
        }
    }

    @Override
    public void registerRepresationAccount(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        account.setRepresentedAccount(true);
        accountRepository.save(account);
    }

    @Override
    public List<Account> findRepresentationAccountsByUserNames(List<String> userName) {
        return accountRepository.findAllByIsRepresentedAccountTrueAndUserNameIn(userName);
    }


}
