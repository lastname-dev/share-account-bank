package com.bank.shareaccount.user.entity;

import com.bank.shareaccount.group.entity.Group;
import com.bank.shareaccount.group.entity.Group_User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @Column(name = "refresh_token")
    private String refreshToken; // 리프레시 토큰


    @OneToMany(mappedBy = "user")
    private List<Group_User> groups = new ArrayList<>();

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}