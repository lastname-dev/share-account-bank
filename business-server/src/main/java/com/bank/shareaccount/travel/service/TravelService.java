package com.bank.shareaccount.travel.service;

import com.bank.shareaccount.travel.dto.TravelListDto;
import com.bank.shareaccount.travel.dto.TravelMemoDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TravelService {
    public void createMemo(TravelMemoDto travelMemoDto,long groupId);
    public List<TravelListDto> getTravels(String userName);

    public Object getTravelInfo( long travelId);
}
