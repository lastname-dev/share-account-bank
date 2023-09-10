package com.bank.shareaccount.account.dto.response;

import com.bank.shareaccount.group.Money;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class GroupAccountInfoDto {
    String groupName;
    String account;
    int goal;
    int balance;
    int dues;
    int duesDate;
    LocalDate startDate;
    LocalDate endDate;
    int limitMember;
    String money;
    List<GroupMemberDto> participants;
}
