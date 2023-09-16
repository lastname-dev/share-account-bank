package com.bank.shareaccount.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelListDto {
    Long travelId;
    String groupName;
    LocalDate startDate;
    LocalDate endDate;
    String photo;
}
