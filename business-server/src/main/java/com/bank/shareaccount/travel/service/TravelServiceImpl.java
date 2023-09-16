package com.bank.shareaccount.travel.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.bank.shareaccount.global.s3.S3UploadService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TravelServiceImpl implements TravelService {

    private final S3UploadService uploadService;
    private final TravelRepository travelRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final Group_UserRepository group_userRepository;

    @Override
    public void createMemo(String comment, MultipartFile file, long groupId) throws IOException {
        Travel travel = new Travel();
        Group group = groupRepository.findById(groupId).get();
        String url = uploadService.upload(file);
        travel.setMemo(comment);
        travel.setGroup(group);
        travel.setUrl(url);
        Travel save = travelRepository.save(travel);
        travel.setTravelId(save.getTravelId());
        group.setTravel(travel);
    }

    @Override
    public List<TravelListDto> getTravels(String userName) {
        User user = userRepository.findById(userName).get();
        List<Group_User> byUser = group_userRepository.findByUser(user);
        List<TravelListDto> travels = new ArrayList<>();
        for (Group_User group_user : byUser) {
            Group group = group_user.getGroup();
            if (group.getTravel()!=null) {
                Travel travel = group.getTravel();
                travels.add(new TravelListDto(travel.getTravelId(),group.getName(),group.getStartDate(),group.getEndDate(),travel.getUrl()));
            }
        }
        return travels;
    }

    @Override
    public Object getTravelInfo(long travleId) {
        return null;
    }
}
