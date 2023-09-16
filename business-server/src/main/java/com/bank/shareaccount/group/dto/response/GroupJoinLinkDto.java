package com.bank.shareaccount.group.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupJoinLinkDto {
    private String groupName;
    private Long groupId;
    private String leader;
    private boolean isUsed;
    private int goal; // 목표 금액
    private int duesDate;// 매달 자동이체 일
    private int dues;
    private String inviter;

}
