package com.bank.shareaccount.global.config.jwt;

import com.auth0.jwt.JWT;
import com.bank.shareaccount.user.entity.User;
import com.bank.shareaccount.user.repository.UserRepository;
import com.bank.shareaccount.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final String[] permitUrls;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        for (var permitUrl : permitUrls) {
            if (request.getRequestURI().equals(permitUrl)) {
                chain.doFilter(request, response);
                return;
            }
        }

        log.info("doFilterInternal ");
        String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);

        // 1. refresh != null  ->  accessToken 만료로 인한 재발급 요청
        if (refreshToken != null) {
            jwtService.checkRefreshToken(response, refreshToken);
            return;
        }

        // 2. accessToken 확인
        String accessToken = jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);

        if (accessToken == null) {
            return;
        }

        String id = (JWT.require(jwtService.algorithm()).build()).verify(accessToken).getClaim("id").toString();

        User user = userRepository.findById(id).get();

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .build();

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(userDetails, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }
}