package com.bank.shareaccount.travel.service;

import com.bank.shareaccount.travel.dto.TravelListDto;
import com.bank.shareaccount.travel.dto.TravelMemoDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TravelService {
    public void createMemo(String comment, MultipartFile file,long groupId) throws IOException;
    public List<TravelListDto> getTravels(String userName);

    public Object getTravelInfo( long travelId);
}
