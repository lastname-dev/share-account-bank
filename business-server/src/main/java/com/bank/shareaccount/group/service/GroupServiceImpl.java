package com.bank.shareaccount.group.service;

import com.bank.shareaccount.group.dto.request.GroupMakeDto;
import com.bank.shareaccount.group.dto.request.GroupUpdateDto;
import com.bank.shareaccount.group.dto.response.GroupInfoDto;
import com.bank.shareaccount.group.dto.response.GroupJoinLinkDto;
import com.bank.shareaccount.group.entity.Access;
import com.bank.shareaccount.group.entity.Group;
import com.bank.shareaccount.group.entity.Group_User;
import com.bank.shareaccount.group.entity.Link;
import com.bank.shareaccount.group.repository.AccessRepository;
import com.bank.shareaccount.group.repository.GroupRepository;
import com.bank.shareaccount.group.repository.Group_UserRepository;
import com.bank.shareaccount.group.repository.LinkRepository;
import com.bank.shareaccount.notification.repository.NotificationRepository;
import com.bank.shareaccount.notification.Type;
import com.bank.shareaccount.notification.entity.Notification;
import com.bank.shareaccount.user.entity.User;
import com.bank.shareaccount.user.repository.UserRepository;
import com.bank.shareaccount.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final UserServiceImpl userService;

    private final GroupRepository groupRepository;
    private final NotificationRepository notificationRepository;
    private final AccessRepository accessRepository;
    private final Group_UserRepository group_userRepository;
    private final LinkRepository linkRepository;

    @Override
    public Long make(GroupMakeDto groupMakeDto, UserDetails userDetails) {
        User user = userService.getUserById(userDetails.getUsername());
        Group group = groupMakeDto.toEntity();

        Group_User group_user = Group_User.builder()
                .build();

        user.joinGroup(group_user);
        group.addMember(group_user);
        group.setLeader(user);

        Group save = groupRepository.save(group);
        group_userRepository.save(group_user);

        return save.getGroupId();
    }

    @Override
    public void exit(String groupName) {
        // 어느 특정 사용자가 해당 아이디의 그룹을 탈퇴하고 싶음
        // 탈퇴하려면 일단 방장의 승인을 받아야함 = 방장한테 알림을 줘야함
        Group group = getGroupByName(groupName);

        // 이제 리더한테 알림을 주면됨
        User leader = group.getLeader();
    }

    @Override
    public void approvalExit(String groupName, String exitUserId) {
        Group group = getGroupByName(groupName);
        User exitUser = userService.getUserById(exitUserId);
 

        Group_User group_user = group_userRepository.findGroup_UserByGroupAndUser(group, exitUser).orElseThrow(IllegalArgumentException::new);

        // 지워야할거
        // 1. 그룹에서 해당 유저를 지움
        group.exitMember(group_user);

        // 2. 유저에서 해당 그룹을 지움
        exitUser.exitGroup(group_user);

        // 3. 그룹유저에서 두개의 외래키로 연결되어있는거 지움
        group_userRepository.delete(group_user);
    }

    @Override
    public void delete(String groupName) {
        Group group = getGroupByName(groupName);
        // 어케 지워야할까

        // 일단 해당 그룹에 포함되어있는애들 다 빼자
        List<Group_User> members = group.getMembers();
        for (var member : members) {
            member.getUser().exitGroup(member);
        }

        // 그룹과 그룹유저는 cascade로 연결되어있으니 지워졌을까? 그랬을듯?
    }

    @Override
    public GroupInfoDto getInfo(String groupName) {
        // 뭘 보여줘야하는지 모르겠다 그냥 다 넘긴다
        GroupInfoDto groupInfoDto = GroupInfoDto.builder().build();
        return GroupInfoDto.from(getGroupByName(groupName));
    }

    @Override
    public void update(GroupUpdateDto groupUpdateDto, String groupName) {
        getGroupByName(groupName).update(groupUpdateDto);
    }

    @Override
    public Group getGroupByName(String groupName) {
        return groupRepository.findByName(groupName).orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public void joinGroup(String userId, String groupName) {
        // 그룹장에게 알림 보내기, access에 넣기.
        log.info("{}가 {}에 가입중 ",userId,groupName);
        Group group = getGroupByName(groupName);
        User sender = userService.getUserById(userId);
        Notification notification = Notification.builder()
                .receiver(group.getLeader())
                .sender(sender)
                .type(Type.JOIN)
                .group(group)
                .build();
        // Access access = Access.builder()
        //         .group(group)
        //         .user(sender)
        //         .build();
        notificationRepository.save(notification);
        addGroup(groupName,userId);
        // accessRepository.save(access);
    }

    @Override
    public void admitJoin(String groupName, String userId) {
        // 그룹에 넣기
        addGroup(groupName, userId);
    }

    @Override
    public void addGroup(String groupId, String userId) {
        User user = userService.getUserById(userId);
        Group group = groupRepository.findByName(groupId).orElseThrow(IllegalAccessError::new);
        Group_User group_user = Group_User.builder().build();

        user.joinGroup(group_user);
        group.addMember(group_user);
        group.setLeader(user);

        group_userRepository.save(group_user);
    }

    public String createJoinLink(long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(IllegalAccessError::new);
        String url = UUID.randomUUID().toString();
        Link link = Link.builder()
                .group(group)
                .url(url)
                .build();
        linkRepository.save(link);
        return url;
    }

    @Override
    public GroupJoinLinkDto link(String linkUri,String userName) {
        Link link = linkRepository.findByUrl(linkUri).orElseThrow(IllegalAccessError::new);
        Group group = link.getGroup();
        GroupJoinLinkDto groupJoinLinkDto = new GroupJoinLinkDto(group.getName(),group.getGroupId(),group.getLeader().getName(),link.isUsed(),group.getGoal(),group.getDuesDate(),group.getDues(),userName);
    
        return groupJoinLinkDto;
    }



    @Override
    public Boolean isLinkValid(String url, String groupId) {
        log.info("요청 url : {}", url);
        Link link = linkRepository.findByUrl(url).orElseThrow(IllegalAccessError::new);
        if(link.getGroup().getName().equals(groupId) && !link.isUsed()){
            link.setUsed();
            linkRepository.save(link);
            return true;
        }
        return false;

    }

    @Override
    public List<User> getMembers(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        List<Group_User> byGroup = group_userRepository.findByGroup(group);
        List<User> users = new ArrayList<>();
        for(Group_User gu : byGroup){
            if(gu.getGroup().equals(group))
                users.add(gu.getUser());
        }
        return users;
    }
    @Override
    public void startTravel(Long groupId){
        Group group= groupRepository.findById(groupId).get();
        group.setStartDate(LocalDate.now());
        groupRepository.save(group);
    }
}
