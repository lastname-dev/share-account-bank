package com.fourstuad.springbatchserver.repository;

import com.fourstuad.springbatchserver.domain.DuesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuesTransactionRepository extends JpaRepository<DuesTransaction, Long> {
    // 필요한 경우 사용자 정의 쿼리 메서드를 여기에 정의합니다.
}
