package com.forstuad.bankserver.dto.response;

import lombok.Data;

@Data
public class MyAccountDto {
    String accountId;
    Long groupId;
    boolean isGroup;
    int balance;
    boolean isRepresented;
}
