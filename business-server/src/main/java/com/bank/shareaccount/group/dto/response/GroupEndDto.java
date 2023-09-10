package com.bank.shareaccount.group.dto.response;

import com.bank.shareaccount.account.dto.response.GroupAccountInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupEndDto {


    GroupAccountInfoDto groupInfo;
}
