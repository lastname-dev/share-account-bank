package com.bank.shareaccount.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainAccountRequestDto {
    String userName;
    String account;
}
