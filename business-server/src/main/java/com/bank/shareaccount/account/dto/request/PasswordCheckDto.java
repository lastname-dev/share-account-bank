package com.bank.shareaccount.account.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordCheckDto {
	String account;
	String password;
}
