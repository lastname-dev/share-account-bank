package com.bank.shareaccount.account.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetGroupAccountsRequestDto {
	List<Long> groupIds;
}
