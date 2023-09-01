package com.bank.shareaccount.user.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String id;
    @Column(name = "user_name")
    private String name;
    private String password; // 비밀번호
    private String phone;


    @Column(name = "user_account")
    private String account;
//    @Enumerated(EnumType.STRING)
//    private Role role;


    @Column(name = "refresh_token")
    private String refreshToken; // 리프레시 토큰

//
//    public void authorizeUser() {
//        this.role = Role.USER;
//    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}