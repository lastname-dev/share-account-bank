package com.bank.shareaccount.account.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class HistoryDto {
    private Long id;

    private String sender;

    private String receiver;

    private int amount;

    private LocalDateTime dateTime;
    List<HistoryDto> historyList;
    List<GroupMemberDto> participants;
}
