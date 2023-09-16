package com.bank.shareaccount.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAccountDto {
	String accountNumber;
	String code;
}
