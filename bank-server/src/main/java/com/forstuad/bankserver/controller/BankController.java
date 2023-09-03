package com.forstuad.bankserver.controller;

import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.dto.CreateAccountRequestDto;
import com.forstuad.bankserver.dto.DepositAccountRequestDto;
import com.forstuad.bankserver.dto.TransferAccountRequestDto;
import com.forstuad.bankserver.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/accounts")
public class BankController {

    private final AccountService accountService;

    //계좌 생성
    @PostMapping
    public ResponseEntity<Map<String,Object>> create(
            @RequestBody CreateAccountRequestDto createAccountRequestDto
            )
    {
        Map<String, Object> response = new HashMap<>();
        try {
            Long userId = createAccountRequestDto.getUserId();
            Account newAccount = accountService.createAccount(userId);
            response.put("status", "success");
            response.put("account", newAccount);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //계좌 입금
    @PostMapping("/deposit")
    public ResponseEntity<Map<String,Object>> deposit(
            @RequestBody DepositAccountRequestDto depositAccountRequestDto
    )
    {
        Map<String, Object> response = new HashMap<>();
        try {
            accountService.deposit(
                    depositAccountRequestDto.getUserId(),
                    depositAccountRequestDto.getAmount()
            );
            response.put("status", "success");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //계좌 인증 (미구현)
    @PostMapping("/verification")
    public ResponseEntity<Map<String,Object>> verification(){
        Map<String,Object> response = new HashMap<>();

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    // 계좌 이체
    @PostMapping("/transactions")
    public ResponseEntity<Map<String, Object>> transfer(
            @RequestBody TransferAccountRequestDto transferAccountRequestDto
    ){
        Map<String, Object> response = new HashMap<>();

        try {
            accountService.transferAccount(
                    transferAccountRequestDto.getSender(),
                    transferAccountRequestDto.getReceiver(),
                    transferAccountRequestDto.getAmount()
            );
            response.put("status", "success");
            response.put("message", "Transfer successful");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "failed");
            response.put("message", "Transfer failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //전체 계좌 조회
    @GetMapping
    public ResponseEntity<Map<String,Object>> accountList(){
        Map<String,Object> response = new HashMap<>();

        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    //계좌 상세 조회

    //그룸계좌 해제
}
