package com.fourstuad.springbatchserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DuesTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long groupId;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private int amount;

    // 생성자, getter 및 setter 구현
}