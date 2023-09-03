package com.forstuad.bankserver.dto;

import lombok.Data;

@Data
public class DepositAccountRequestDto {
    private Long userId;
    private int amount;
}
