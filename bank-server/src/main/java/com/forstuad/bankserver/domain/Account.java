package com.forstuad.bankserver.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName; //계좌이름

    @Column(name = "group_id")
    private long groupId;//그룹의 아이디

    @Column(name = "account_id")
    private String accountId;//계좌번호

    private int balance;//공동계좌 금액

    private boolean isGroup;//그룹인지

    private int goal;//목표금액

    private String groupName;//그룹이름


    private int dues;

    private int duesDate;

    private LocalDateTime startDate;

    private int member;

    private String money;

    private boolean isRepresentedAccount;//대표 계좌 여부

    private boolean settlementStatus;//정산여부

    @Builder
    public Account(String userName, long groupId, String accountId, int balance, boolean isGroup) {
        this.userName = userName;
        this.groupId = groupId;
        this.accountId = accountId;
        this.balance = balance;
        this.isGroup = isGroup;
    }
}

