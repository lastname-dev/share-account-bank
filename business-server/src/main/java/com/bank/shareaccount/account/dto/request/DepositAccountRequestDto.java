package com.bank.shareaccount.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositAccountRequestDto {
    private String userName;
    private String accountId;
    private int amount;
}
