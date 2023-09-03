package com.forstuad.bankserver.service;

import com.forstuad.bankserver.domain.Account;

public interface AccountService {
    public void saveAccount(Account account);
    public Account findByUserId(Long userId);
    public void deposit(Long userId,int balance);
    public Account createAccount(Long userId);
    public void transferAccount(String sender,String receiver,int amount);

}
