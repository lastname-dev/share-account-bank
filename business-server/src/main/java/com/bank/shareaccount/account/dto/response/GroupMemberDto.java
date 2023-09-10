package com.bank.shareaccount.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupMemberDto {
    String userName; //본명
    String email;
}
