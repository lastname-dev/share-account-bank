package com.bank.shareaccount.account.service;

import com.bank.shareaccount.account.dto.request.AccountCodeDto;
import com.bank.shareaccount.account.dto.request.AccountNumberDto;
import com.bank.shareaccount.account.dto.request.AllAccountRequestDto;
import com.bank.shareaccount.account.dto.request.CreateAccountRequestDto;
import com.bank.shareaccount.account.dto.request.DepositAccountRequestDto;
import com.bank.shareaccount.account.dto.request.GetGroupAccountsRequestDto;
import com.bank.shareaccount.account.dto.request.PasswordCheckDto;
import com.bank.shareaccount.account.dto.request.PasswordRequestDto;
import com.bank.shareaccount.account.dto.request.TransferAccountRequestDto;
import com.bank.shareaccount.account.dto.request.*;
import com.bank.shareaccount.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "${feign.bank.name}", url = "${feign.bank.url}", configuration = FeignConfig.class)
public interface BankServerFeign {

    @PostMapping("/accounts")
    ResponseEntity<Map<String, Object>> create(@RequestBody CreateAccountRequestDto createAccountRequestDto);

    @PostMapping("/accounts/deposit")
    ResponseEntity<Map<String, Object>> deposit(@RequestBody DepositAccountRequestDto depositAccountRequestDto);

    @PostMapping("/accounts/transactions")
    ResponseEntity<Map<String, Object>> transactions(@RequestBody TransferAccountRequestDto transferAccountRequestDto);

    @GetMapping("/accounts/my/{userName}")
    ResponseEntity<?> getAccounts(@PathVariable String userName);

    @PostMapping("/accounts/group")
    ResponseEntity<Map<String, Object>> getGroupAccounts(@RequestBody GetGroupAccountsRequestDto groupIds);
    @GetMapping("/accounts/{accountNumber}")
    ResponseEntity<Map<String, Object>> getDetail(@PathVariable String accountNumber);

    @DeleteMapping("/accounts/group/{accountNumber}")
    ResponseEntity<?> deleteGroupAccounts(@PathVariable String accountNumber);

    @PostMapping("/accounts/group/{accountNumber}")
    ResponseEntity<?> assignGroupAccounts(@PathVariable String accountNumber);

    @GetMapping("/accounts/{accountsNumber}")
    ResponseEntity<Map<String, Object>> getDetailGroupAccount(@PathVariable String accountsNumber);

    @PostMapping("/accounts/group/{accountNumber}")
    ResponseEntity<Map<String, Object>> assignGroupAccount(@RequestBody long groupId, @PathVariable String accountNumber);

    @PostMapping("/accounts/caculation/{group}")
    ResponseEntity<Map<String,Object>> calcualteMoney(@RequestBody GroupUserRequestDto groupUserRequestDto,@PathVariable long group);

    @PostMapping("/accounts/main")
    ResponseEntity<Map<String,Object>> assignMainAccount(@RequestBody MainAccountRequestDto mainAccountRequestDto);
    @PostMapping("/accounts/verification")
    ResponseEntity<?> accountVerificationSend(@RequestBody AccountCodeDto accountCodeDto);

    @PostMapping("/accounts/host")
    ResponseEntity<?> checkHost(@RequestBody AccountNumberDto accountNumberDto);

    @PostMapping("/accounts/password")
    ResponseEntity<?> checkPassword(@RequestBody PasswordCheckDto passwordCheckDto);
}
