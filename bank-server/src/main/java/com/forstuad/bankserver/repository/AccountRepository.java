package com.forstuad.bankserver.repository;

import com.forstuad.bankserver.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountId(String accountId);
    Account findByUserId(Long userId);
}
