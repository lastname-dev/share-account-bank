package com.bank.shareaccount.global.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bank.shareaccount.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.access.header}")
    private String ACCESS_HEADER;

    @Value("${jwt.refresh.header}")
    private String REFRESH_HEADER;

    @Value("${jwt.access.expiration}")
    private int ACCESS_EXPIRATION_TIME;

    @Value("${jwt.refresh.expiration}")
    private int REFRESH_EXPIRATION_TIME;

    @Value("${jwt.secretKey}")
    private String secretKey;


    private static final String PREFIX = "Bearer ";
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";

    UserRepository userRepository;


    public String createAccessToken(String id) {
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
                .withClaim("id", id)
                .sign(algorithm());
    }


    public String createRefreshToken() {
        Date now = new Date();
        return JWT.create()
                .withSubject(REFRESH_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + REFRESH_EXPIRATION_TIME))
                .sign(algorithm());
    }

    public Algorithm algorithm() {
        return Algorithm.HMAC512(secretKey);
    }

    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_HEADER))
                .filter(token -> token.startsWith(PREFIX))
                .map(token -> token.replace(PREFIX, ""));
    }

    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(REFRESH_HEADER))
                .filter(token -> token.startsWith(PREFIX))
                .map(token -> token.replace(PREFIX, ""));
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(algorithm()).build().verify(token);
            return true;
        } catch (Exception e) {
            //log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }

    public void sendBothToken(HttpServletResponse response, String accessToken, String refreshToken) {
        sendAccessToken(response, accessToken);
        sendRefreshToken(response, refreshToken);
    }

    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.addHeader(ACCESS_HEADER, PREFIX + accessToken);
    }

    public void sendRefreshToken(HttpServletResponse response, String refreshToken) {
        response.addHeader(REFRESH_HEADER, PREFIX + refreshToken);
    }
    public void checkRefreshToken(HttpServletResponse response, String refreshToken) {
        // todo jwt service로 이동할것
        userRepository.findByRefreshToken(refreshToken)
                .ifPresent(user -> {
                    String newAccessToken = createAccessToken(user.getId());
                    String newRefreshToken = createRefreshToken();

                    user.updateRefreshToken(newRefreshToken);

                    sendBothToken(response, newAccessToken, newRefreshToken);
                });
    }
}
