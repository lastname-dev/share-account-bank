package com.bank.shareaccount.travel.service;

import com.bank.shareaccount.group.entity.Group;
import com.bank.shareaccount.group.entity.Group_User;
import com.bank.shareaccount.group.repository.GroupRepository;
import com.bank.shareaccount.group.repository.Group_UserRepository;
import com.bank.shareaccount.travel.dto.TravelListDto;
import com.bank.shareaccount.travel.dto.TravelMemoDto;
import com.bank.shareaccount.travel.entity.Travel;
import com.bank.shareaccount.travel.repository.TravelRepository;
import com.bank.shareaccount.user.entity.User;
import com.bank.shareaccount.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    private final TravelRepository travelRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final Group_UserRepository group_userRepository;

    @Override
    public void createMemo(TravelMemoDto travelMemoDto, long groupId) {
        Travel travel = new Travel();
        Group group = groupRepository.findById(groupId).get();
        travel.setMemo(travelMemoDto.getComment());
        travel.setGroup(group);
        travelRepository.save(travel);
    }

    @Override
    public List<TravelListDto> getTravels(String userName) {
        User user = userRepository.findById(userName).get();
        List<Group_User> byUser = group_userRepository.findByUser(user);
        List<TravelListDto> travels = new ArrayList<>();
        for (Group_User group_user : byUser) {
            Group group = group_user.getGroup();
            if (group.getEndDate() != null) {
                Travel travel = travelRepository.findByGroup(group).get();
                travels.add(new TravelListDto(travel.getTravelId(),group.getName(),group.getStartDate(),group.getEndDate()));
            }
        }
        return travels;
    }

    @Override
    public Object getTravelInfo(long travleId) {
        return null;
    }
}
