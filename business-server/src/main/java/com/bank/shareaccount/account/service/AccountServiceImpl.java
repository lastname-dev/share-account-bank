package com.bank.shareaccount.account.service;

import com.bank.shareaccount.account.dto.request.*;
import static com.bank.shareaccount.email.service.EmailServiceImpl.*;

import com.bank.shareaccount.account.dto.request.AccountCodeDto;
import com.bank.shareaccount.account.dto.request.AccountNumberDto;
import com.bank.shareaccount.account.dto.request.AllAccountRequestDto;
import com.bank.shareaccount.account.dto.request.CheckAccountDto;
import com.bank.shareaccount.account.dto.request.CreateAccountRequestDto;
import com.bank.shareaccount.account.dto.request.DepositAccountRequestDto;
import com.bank.shareaccount.account.dto.request.ExchangeRequest;
import com.bank.shareaccount.account.dto.request.GetGroupAccountsRequestDto;
import com.bank.shareaccount.account.dto.request.PasswordCheckDto;
import com.bank.shareaccount.account.dto.request.TransferAccountRequestDto;
import com.bank.shareaccount.account.dto.response.AccountHostResponseDto;
import com.bank.shareaccount.account.dto.response.GroupAccountInfoDto;
import com.bank.shareaccount.account.dto.response.GroupAccountsDto;
import com.bank.shareaccount.account.dto.response.GroupMemberDto;
import com.bank.shareaccount.group.entity.Group;
import com.bank.shareaccount.group.entity.Group_User;
import com.bank.shareaccount.group.repository.GroupRepository;
import com.bank.shareaccount.group.repository.Group_UserRepository;
import com.bank.shareaccount.group.service.GroupService;
import com.bank.shareaccount.user.entity.User;
import com.bank.shareaccount.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class AccountServiceImpl {
    private final BankServerFeign bankServerFeign;
    private final ShinhanServerFeign shinhanServerFeign;
    private final Group_UserRepository group_userRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final GroupService groupService;
    private final RedisTemplate redisTemplate;
    public ResponseEntity<Map<String, Object>> accountOpening(CreateAccountRequestDto createAccountRequestDto) {
        log.info("accountOpening");
        return bankServerFeign.create(createAccountRequestDto);
    }

    public ResponseEntity<Map<String, Object>> deposit(DepositAccountRequestDto depositAccountRequestDto) {
        return bankServerFeign.deposit(depositAccountRequestDto);
    }

    public ResponseEntity<Map<String, Object>> transactions(TransferAccountRequestDto transferAccountRequestDto,String userName) {
        Group group = groupRepository.findByAccount(transferAccountRequestDto.getReceiver());
        if(group!=null) {
            User user = userRepository.findById(userName).get();
            List<Group_User> byUser = group_userRepository.findByUser(user);
            for(Group_User gu : byUser){
                if(gu.getGroup().getGroupId().equals(group.getGroupId())){
                    log.info("Zzzzzz");
                    gu.pay();
                    group_userRepository.save(gu);
                    break;
                }
            }
        }
        return bankServerFeign.transactions(transferAccountRequestDto);
    }

    public ResponseEntity<?> getAccounts(AllAccountRequestDto allAccountRequestDto) {
        return bankServerFeign.getAccounts(allAccountRequestDto.getUserName());
    }

    public ResponseEntity<?> getGroupAccounts(String userName){
        User user = userRepository.findById(userName).get();
        List<Group_User> users = group_userRepository.findAllByUser(user);
        List<Long> groupIds = new ArrayList<>();
        List<GroupAccountsDto> dtos = new ArrayList<>();
        for(Group_User u: users){
            groupIds.add(u.getGroup().getGroupId());
            // Group group = u.getGroup();
            // groups.add(group);
            // GroupAccountsDto groupAccountsDto = new GroupAccountsDto(group.getGroupId(),group.getName(),group.getAccount(),group.getGoal(),0,group.getDues(),group.getDuesDate(),group.getStartDate(),group.getLimit(),group.getMoney().toString(),false);
            // dtos.add(groupAccountsDto);
        }
        GetGroupAccountsRequestDto dto = new GetGroupAccountsRequestDto(groupIds);
        log.info("groupIds : {}",groupIds );
        if(groupIds.size()==0){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        ArrayList groupAccounts = (ArrayList)(bankServerFeign.getGroupAccounts(dto).getBody().get("groupAccounts"));
        for(int i=0;i<groupAccounts.size();i++){
            LinkedHashMap o = (LinkedHashMap)groupAccounts.get(i);
            Group group = groupRepository.findByAccount((String)o.get("account"));
            Group_User gu = group_userRepository.findGroup_UserByGroupAndUser(group, user).get();
            boolean check=false;
            if(group.getEndDate()==null && group.getStartDate().isBefore(LocalDate.now())){
                check=true;
            }
            GroupAccountsDto temp = new GroupAccountsDto(group.getGroupId(),group.getName(),group.getAccount(),group.getGoal(),Integer.parseInt(o.get("balance").toString()),group.getDues(),group.getDuesDate(),group.getStartDate(),group.getLimit(),group.getMoney().toString(),gu.isPaid(),check);
            dtos.add(temp);
        }
        return new ResponseEntity<>(dtos,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Map<String, Object>> getDetail(String accountNumber) {
        return bankServerFeign.getDetail(accountNumber);
    }

    public ResponseEntity<Map<String, Object>> getDetailGroupAccount(String accountNumber) {
        return bankServerFeign.getDetailGroupAccount(accountNumber);
    }

    public ResponseEntity<Map<String, Object>> assignGroupAccount(Long groupId, String accountNumber) {
        log.info("groupId : {} , accountNumber: {}",groupId,accountNumber);
        return bankServerFeign.assignGroupAccount(groupId, accountNumber);
    }

    public ResponseEntity<GroupAccountInfoDto> getGroupInfo(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        int balance = (Integer)bankServerFeign.getDetail(group.getAccount()).getBody().get("balance");
        List<User> members = groupService.getMembers(groupId);
        List<GroupMemberDto> groupMemberDtos = new ArrayList<>();
        for(User user : members){
            GroupMemberDto groupMemberDto = new GroupMemberDto(user.getName(),user.getId());
            groupMemberDtos.add(groupMemberDto);
        }
        GroupAccountInfoDto groupAccountInfoDto = new GroupAccountInfoDto();
        groupAccountInfoDto.setAccount(group.getAccount());
        groupAccountInfoDto.setGroupName(group.getName());
        groupAccountInfoDto.setBalance(balance);
        groupAccountInfoDto.setDues(group.getDues());
        groupAccountInfoDto.setMoney(group.getMoney().toString());
        groupAccountInfoDto.setGoal(group.getGoal());
        groupAccountInfoDto.setDuesDate(group.getDuesDate());
        groupAccountInfoDto.setLimitMember(group.getLimit());
        groupAccountInfoDto.setStartDate(group.getStartDate());
        groupAccountInfoDto.setParticipants(groupMemberDtos);
        groupAccountInfoDto.setEndDate(group.getEndDate());


        return new ResponseEntity<GroupAccountInfoDto>(groupAccountInfoDto, HttpStatus.ACCEPTED);
    }
//    public
    public Object getExchangeMoney(ExchangeRequest request){
        Object exchangeMoney= shinhanServerFeign.getExchangeMoney(request);
        log.info("테스트 : {} , {} ", exchangeMoney,exchangeMoney.getClass());
        return exchangeMoney;
    }
    public ResponseEntity<Map<String, Object>> calcualteMoney(long groupId){
        Group group = groupRepository.findById(groupId).get();
        List<Group_User> groups = group_userRepository.findByGroup(group);
        List<String> users = new ArrayList<>();
        for(Group_User group_user : groups){
            users.add(group_user.getUser().getName());
        }
        log.info(String.valueOf(users));
        return bankServerFeign.calcualteMoney(new GroupUserRequestDto(users),groupId);
    }
    public ResponseEntity<Map<String, Object>> assignMainAccount(MainAccountRequestDto mainAccountRequestDto){
        return bankServerFeign.assignMainAccount(mainAccountRequestDto);
    }
    public ResponseEntity<?> endTravel(long groupId){
        Group group = groupRepository.findById(groupId).get();
        String account = group.getAccount();
        group.setEndDate(LocalDate.now());
        groupRepository.save(group);
        Map<String, Object> map1 = bankServerFeign.getDetail(account).getBody();
        GroupAccountInfoDto dto = getGroupInfo(groupId).getBody();
        map1.put("group",dto);
        return new ResponseEntity<>(map1,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getHost(String accountsNumber) {
        return bankServerFeign.checkHost(new AccountNumberDto(accountsNumber));
    }

    public ResponseEntity<?> checkPassword(PasswordCheckDto request) {
        return bankServerFeign.checkPassword(request);
    }

    public ResponseEntity<?> send1Won(AccountNumberDto request) {
        String ePw = createKey();
        redisTemplate.opsForValue().set(ePw,request.getAccountNumber(),120*1L);
        return bankServerFeign.accountVerificationSend(new AccountCodeDto(ePw,request.getAccountNumber()));
    }

    public ResponseEntity<?> checkCode(CheckAccountDto request) throws ChangeSetPersister.NotFoundException {
        String check = (String) redisTemplate.opsForValue().get(request.getCode());
        if(check==null){
            throw new ChangeSetPersister.NotFoundException();
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
