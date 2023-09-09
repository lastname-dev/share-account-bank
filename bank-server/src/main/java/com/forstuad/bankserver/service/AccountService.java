package com.forstuad.bankserver.service;

import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.domain.CashFlow;
import com.forstuad.bankserver.dto.CashFlowHistory;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountService {
    public void saveAccount(Account account);
    public Account findByAccountId(String accountId);
    public void deposit(String accountId,int balance);
    public Account createAccount(String userName);
    public void transferAccount(String sender,String receiver,int amount);
    public List<CashFlowHistory> getCashFlowList(String accountId);
    public List<Account> findAllByUserName(String userName);
    public void disableGroupAccount(String accountId);
    public void assignGroupAccount(String accountId,Long groupId);
    public List<Account> findGroupAccountByGroupIds(List<Long> groupIds);
    public List<Account> findAccountByGroupId(Long groupId); //그룹 아이디로 Account 계정들 가져오기

    public List<String> getAccountsNames(List<Account> accountList); // 계좌들로부터 사용자 이름들 얻어오기

    public Account findRepresentAccount(Long groupId);//그룹아이디로 대표계좌 찾기
    public void caculateAccountByN(Account representationAccount,List<Account> representedAccounts,int balance);//정산하기
    public void registerRepresationAccount(String accountId);//대표계좌 지정하기

    public List<Account> findRepresentationAccountsByUserNames(List<String> userName);//이름을 토대로 대표계좌들 찾기

}
