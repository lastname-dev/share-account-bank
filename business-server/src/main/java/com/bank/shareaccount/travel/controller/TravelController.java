package com.bank.shareaccount.travel.controller;

import com.bank.shareaccount.travel.dto.TravelMemoDto;
import com.bank.shareaccount.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class TravelController {

    private final TravelService travelService;

    @PostMapping("/groups/{groupId}/comment")
    public ResponseEntity<?> createMemo(@RequestBody TravelMemoDto travelMemoDto, @PathVariable long groupId){
        travelService.createMemo(travelMemoDto,groupId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/travels")
    public ResponseEntity<?> getTravels(@AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(travelService.getTravels(userDetails.getUsername()),HttpStatus.ACCEPTED);
    }
    @GetMapping("/travels/{travleId}")
    public ResponseEntity<?> getTravelInfo(@PathVariable long travelId){
        return new ResponseEntity<>(travelService.getTravelInfo(travelId),HttpStatus.ACCEPTED);
    }
}
