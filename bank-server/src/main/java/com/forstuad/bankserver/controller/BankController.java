package com.forstuad.bankserver.controller;

import com.forstuad.bankserver.domain.Account;
import com.forstuad.bankserver.dto.CashFlowHistory;
import com.forstuad.bankserver.dto.request.*;
import com.forstuad.bankserver.dto.response.AccountResponseDto;
import com.forstuad.bankserver.dto.response.GroupAccountResponseDto;
import com.forstuad.bankserver.dto.response.MyAccountDto;
import com.forstuad.bankserver.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @GetMapping
    public ResponseEntity<Map<String,Object>> accountList(
            @RequestBody AllAccountRequestDto allAccountRequestDto
            )
    {
        Map<String,Object> response = new HashMap<>();

        try {
            String userName = allAccountRequestDto.getUserName();
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
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Map<String,Object>> account(
            @PathVariable String accountNumber
    ){
        Map<String,Object> response = new HashMap<>();

        try {
            Account account = accountService.findByAccountId(accountNumber);
            // AccountResponseDto accountResponseDto = new AccountResponseDto();
            // accountResponseDto.setAccountId(account.getAccountId());
            // accountResponseDto.setBalance(account.getBalance());
            // accountResponseDto.setGroupId(accountResponseDto.getGroupId());
            // accountResponseDto.setGroup(accountResponseDto.isGroup());

            //계좌 입출금 내역
            List<CashFlowHistory> cashFlowList = accountService.getCashFlowList(accountNumber);

            response.put("accountNumber",accountNumber);
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
    @PostMapping("/group/{accountNumber}")
    public ResponseEntity<?> assignGroupAccount(@PathVariable String accountNumber,@RequestBody Long groupId){
        accountService.assignGroupAccount(accountNumber,groupId);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    //그룹계좌 해제
    @DeleteMapping("/group/{accountNumber}")
    public ResponseEntity<Map<String,Object>> disableGroup(
            @PathVariable String accountNumber
    ){
        Map<String,Object> response = new HashMap<>();

        try {
            accountService.disableGroupAccount(accountNumber);
            response.put("message","group disabled success");
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            response.put("status", "failed");
            response.put("message", "Disabling Group failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //사용자의 그룹계좌 목록 조회
    @PostMapping("/group")
    public ResponseEntity<Map<String,Object>> MyGroupAccounts(
            @RequestBody GroupIdsRequestDto groupIdsRequestDto
            ){
        Map<String,Object> response = new HashMap<>();

        try {
            List<Long> groupIds = groupIdsRequestDto.getGroupIds();
            List<Account> groupAccountList = accountService.findGroupAccountByGroupIds(groupIds);
            log.info(String.valueOf(groupAccountList.get(0)));
            List<GroupAccountResponseDto> groupAccountResponseDtoList = new ArrayList<>();
            for(Account account : groupAccountList){
                GroupAccountResponseDto groupAccountResponseDto = GroupAccountResponseDto.builder()
                        .groupName(account.getGroupName())
                        .account(account.getAccountId())
                        .goal(account.getGoal())
                        .balance(account.getBalance())
                        .dues(account.getDues())
                        .duesDate(account.getDuesDate())
                        .startDate(account.getStartDate())
                        .member(groupAccountList.size())
                        .money(account.getMoney())
                        .build();

                groupAccountResponseDtoList.add(groupAccountResponseDto);
            }
            response.put("groupAccounts",groupAccountResponseDtoList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/my/{userName}")
    public ResponseEntity<Map<String,Object>> getAccounts(@PathVariable String userName){
        Map<String,Object> response = new HashMap<>();

        try {
            List<Account> allByUserName = accountService.findAllByUserName(userName);
            List<MyAccountDto> myAccountDtoList = new ArrayList<>();

            for (Account account : allByUserName) {
                MyAccountDto myAccountDto = new MyAccountDto();
                myAccountDto.setAccountId(account.getAccountId());
                myAccountDto.setGroupId(account.getGroupId());
                myAccountDto.setGroup(account.isGroup());
                myAccountDto.setBalance(account.getBalance());
                myAccountDto.setRepresented(account.isRepresentedAccount());
                myAccountDtoList.add(myAccountDto);
            }
            response.put("accountList",myAccountDtoList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/caculation/{groupId}")
    public ResponseEntity<Map<String,Object>> caculation(
            @PathVariable long groupId,
            @RequestBody GroupIdRequestDto groupIdRequestDto
    ){
        Map<String,Object> response = new HashMap<>();
        try {
            //이름을 토대로 각각의 대표 계좌 가져오기
            //대표 Account들 N빵하기
            List<String> accountNames = groupIdRequestDto.getUserName();
            List<Account> representationAccounts = accountService.findRepresentationAccountsByUserNames(accountNames);
            //그룹 계좌의 대표 계좌
            Account representAccount = accountService.findRepresentAccount(groupId);
            int balance = representAccount.getBalance();
            accountService.caculateAccountByN(representAccount,representationAccounts,balance);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/main")
    public ResponseEntity<Map<String,Object>> registerRepresentationAccount(
            @RequestBody ReprensativeRequestDto reprensativeRequestDto
    ){
        Map<String,Object> response = new HashMap<>();
        try {
            String userName = reprensativeRequestDto.getUserName();
            String accountId = reprensativeRequestDto.getAccount();

            accountService.registerRepresationAccount(accountId);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
