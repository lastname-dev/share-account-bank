package com.bank.shareaccount.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountRequestDto {
    private String userName;
}
