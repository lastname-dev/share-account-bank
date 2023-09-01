package com.bank.shareaccount.user.service;

import com.bank.shareaccount.user.dto.request.UserChangePasswordDto;
import com.bank.shareaccount.user.dto.request.UserSignUpDto;
import com.bank.shareaccount.user.dto.request.UserUpdateDto;
import com.bank.shareaccount.user.dto.response.UserInfoResponseDto;
import com.bank.shareaccount.user.dto.response.UserTokenResponseDto;
import com.bank.shareaccount.user.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    UserTokenResponseDto signUp(UserSignUpDto userSignUpDto);
    void updateUserInfo(UserUpdateDto userUpdateDto);
    UserInfoResponseDto getUserInfo(String userId);
    void changePassword(UserChangePasswordDto userChangePasswordDto);
    void checkRefreshToken(HttpServletResponse response, String refreshToken);
    User getUserById(String id);
}
