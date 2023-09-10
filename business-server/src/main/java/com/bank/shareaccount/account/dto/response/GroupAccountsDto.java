package com.bank.shareaccount.account.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupAccountsDto {
	private long groupId;
	private String groupName;
	private String account;
	private int goal;
	private int balance;
	private int dues;
	private int duesDate;
	private LocalDate startDate;
	private int member;
	private String money;
	private boolean isPaid;
	private boolean isTravel;
}
