package com.forstuad.bankserver.service;

import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.domain.CashFlow;
import com.forstuad.bankserver.dto.CashFlowHistory;
import java.util.List;

public interface AccountService {
    public void saveAccount(Account account);
    public Account findByAccountId(String accountId);
    public void deposit(String accountId,int balance);
    public Account createAccount(Long userId);
    public void transferAccount(String sender,String receiver,int amount);
    public List<CashFlowHistory> getCashFlowList(String accountId);
    public List<Account> findAllByUserId(Long userId);
    public void disableGroupAccount(String accountId);
}