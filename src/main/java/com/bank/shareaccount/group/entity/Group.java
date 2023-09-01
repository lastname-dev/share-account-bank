//package com.bank.shareaccount.group.entity;
//
//import com.bank.shareaccount.group.Money;
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//@Builder
//@AllArgsConstructor
//public class Group {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "group_id")
//    private long groupId;
//    @Column(name = "group_name")
//    private String name;
//    private int leader;
//    @Column(name = "group_account")
//    private String account;
//    private int goal; // 목표 금액
//    @Column(name ="dues_date")
//    private int duesDate;// 매달 자동이체 일
//    private int dues; // 매달 자동이체 금액
//    @Column(name="limit_users")
//    private int limit; // 그룹 제한 인원
//    private Date startDate;
//    private Money money; // 가고자 하는 나라의 화폐 단위
//    private String link;
//}
