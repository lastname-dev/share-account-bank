package com.forstuad.bankserver.controller;

import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.dto.CashFlowHistory;
import com.forstuad.bankserver.dto.request.AllAccountRequestDto;
import com.forstuad.bankserver.dto.request.CreateAccountRequestDto;
import com.forstuad.bankserver.dto.request.DepositAccountRequestDto;
import com.forstuad.bankserver.dto.request.TransferAccountRequestDto;
import com.forstuad.bankserver.dto.response.AccountResponseDto;
import com.forstuad.bankserver.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            String userName = createAccountRequestDto.getUserName();
            Account newAccount = accountService.createAccount(userName);
            response.put("account", newAccount);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
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
                    depositAccountRequestDto.getAccountId(),
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
            response.put("message", "Transfer successful");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("message", "Transfer failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //전체 계좌 조회
    @GetMapping("/{userName}")
    public ResponseEntity<Map<String,Object>> accountList(
            @PathVariable String userName)
    {
        Map<String,Object> response = new HashMap<>();

        try {
            List<Account> userAccounts = accountService.findAllByUserName(userName);
            List<AccountResponseDto> accountResponseDtos = new ArrayList<>();

            for(Account account : userAccounts){
                AccountResponseDto accountResponseDto = new AccountResponseDto();
                accountResponseDto.setGroup(account.isGroup());
                accountResponseDto.setGroupId(account.getGroupId());
                accountResponseDto.setBalance(account.getBalance());
                accountResponseDto.setAccountId(account.getAccountId());
                accountResponseDtos.add(accountResponseDto);
            }
            response.put("accountList",accountResponseDtos);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "failed");
            response.put("message", "Reading All Account failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //계좌 상세 조회
    @GetMapping("/{accountId}")
    public ResponseEntity<Map<String,Object>> account(
            @PathVariable String accountId
    ){
        Map<String,Object> response = new HashMap<>();

        try {
            Account account = accountService.findByAccountId(accountId);
            // AccountResponseDto accountResponseDto = new AccountResponseDto();
            // accountResponseDto.setAccountId(account.getAccountId());
            // accountResponseDto.setBalance(account.getBalance());
            // accountResponseDto.setGroupId(accountResponseDto.getGroupId());
            // accountResponseDto.setGroup(accountResponseDto.isGroup());

            //계좌 입출금 내역
            List<CashFlowHistory> cashFlowList = accountService.getCashFlowList(accountId);

            response.put("accountId",accountId);
            response.put("balance",account.getBalance());
            response.put("historyList",cashFlowList);

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "failed");
            response.put("message", "Account Reading failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //그룹계좌 지정
    @PostMapping("/{accountId}/group")
    public ResponseEntity<?> assignGroupAccount(@PathVariable String accountId,@RequestBody long groupId){
        accountService.assignGroupAccount(accountId,groupId);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    //그룹계좌 해제
    @DeleteMapping("/{accountId}/group")
    public ResponseEntity<Map<String,Object>> disableGroup(
            @PathVariable String accountId
    ){
        Map<String,Object> response = new HashMap<>();

        try {
            accountService.disableGroupAccount(accountId);
            response.put("message","group disabled success");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            response.put("status", "failed");
            response.put("message", "Disabling Group failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
