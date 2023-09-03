package com.forstuad.bankserver.repository;

import com.forstuad.bankserver.domain.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashFlowRepository extends JpaRepository<CashFlow,Long> {
}
