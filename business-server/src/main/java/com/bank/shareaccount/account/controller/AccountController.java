package com.bank.shareaccount.account.controller;

import com.bank.shareaccount.account.dto.request.*;
import com.bank.shareaccount.account.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> accountOpening(@RequestBody PasswordRequestDto passwordRequestDto, @AuthenticationPrincipal UserDetails userDetails) {

        // 계좌 생성
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto(userDetails.getUsername(),
            passwordRequestDto.getPassword());
        return accountService.accountOpening(createAccountRequestDto);
    }

    @PostMapping("/accounts/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositAccountRequestDto depositAccountRequestDto,@AuthenticationPrincipal UserDetails userDetails) {
        depositAccountRequestDto.setUserName(userDetails.getUsername());
        // 계좌 입금
        return accountService.deposit(depositAccountRequestDto);
    }

    @PostMapping("/accounts/verification")
    public ResponseEntity<?> verification() {
        // 계좌 인증
        return null;
    }

    @PostMapping("/accounts/transactions")
    public ResponseEntity<?> transactions(@RequestBody TransferAccountRequestDto transferAccountRequestDto,@AuthenticationPrincipal UserDetails userDetails) {
        // 계좌 이체
        return accountService.transactions(transferAccountRequestDto,userDetails.getUsername());
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(@AuthenticationPrincipal UserDetails userDetails) {
        // 내 계좌 조회
        AllAccountRequestDto allAccountRequestDto = new AllAccountRequestDto(userDetails.getUsername());
        log.info(userDetails.getUsername());
        ResponseEntity<?> accounts = accountService.getAccounts(allAccountRequestDto);
        log.info("accounts : {}",accounts.getBody());
        return accountService.getAccounts(allAccountRequestDto);
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<?> getDetailAccount(@PathVariable String accountNumber) {
        // 계좌 상세정보 조회
        return accountService.getDetail(accountNumber);
    }


    @PostMapping("/accounts/{accountNumber}")
    public ResponseEntity<?> cancellation(@PathVariable String accountNumber) {
        // 계좌 해지
        return null;
    }

    @PostMapping("/accounts/{accountNumber}/group")
    public ResponseEntity<?> appointAccount(@PathVariable String accountNumber, @RequestBody long groupId) {
        // 그룹계좌 지정
        return accountService.assignGroupAccount(groupId, accountNumber);
    }

    @GetMapping("/exchangeRate")
    public ResponseEntity<?> getExchangeRate() {
        // 환율 우대율 조회
        return null;
    }
    @GetMapping("/groups")
    public ResponseEntity<?> getGroupAccounts(@AuthenticationPrincipal UserDetails userDetails){
        //그룹 계좌 목록 조회
        return accountService.getGroupAccounts(userDetails.getUsername());
    }
    @GetMapping("/groups/{groupId}")
    public ResponseEntity<?> getGroupInfo(@PathVariable Long groupId){
        //그룹 정보 보기
        return accountService.getGroupInfo(groupId);
    }
    @PostMapping("/groups/{groupId}/calculation")
    public ResponseEntity<Map<String, Object>> caclulateMoney(@PathVariable Long groupId){
        return accountService.calcualteMoney(groupId);
    }

    @PostMapping("/accounts/main")
    public ResponseEntity<Map<String, Object>> assignMainAccount(@RequestBody AccountIdRequestDto accountIdRequestDto, @AuthenticationPrincipal UserDetails userDetails){
        log.info("accountId : {}",accountIdRequestDto.getAccountId());
        MainAccountRequestDto mainAccountRequestDto = new MainAccountRequestDto(userDetails.getUsername(), accountIdRequestDto.getAccountId());
        return accountService.assignMainAccount(mainAccountRequestDto);
    }
    @GetMapping("/groups/{groupId}/travel")
    public ResponseEntity<?> endTravel(@PathVariable long groupId){
        return new ResponseEntity<>(accountService.endTravel(groupId),HttpStatus.ACCEPTED);
    }
    //// 신한은행 API
    @PostMapping("/exchange")
    public Object getExchangeMoney(@RequestBody ExchangeRequest request){
        return accountService.getExchangeMoney(request);
    }

    //소유주 찾기
    @PostMapping("/accounts/host")
    public ResponseEntity<?> getHost(@RequestBody AccountHostDto request){
        return accountService.getHost(request.getAccountsNumber());
    }

    @PostMapping("/accounts/password/verification")
    public ResponseEntity<?> checkPassword(@RequestBody PasswordCheckDto request){
        return accountService.checkPassword(request);
    }
    @PostMapping("/accounts/won")
    public ResponseEntity<?> send1Won(@RequestBody AccountNumberDto request){
        return accountService.send1Won(request);
    }
    @PostMapping("/accounts/won/verification")
    public ResponseEntity<?> checkAccounts(@RequestBody CheckAccountDto request) throws
        ChangeSetPersister.NotFoundException {
        return accountService.checkCode(request);
    }
}
