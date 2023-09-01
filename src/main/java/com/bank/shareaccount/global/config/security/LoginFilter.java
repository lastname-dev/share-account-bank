package com.bank.shareaccount.global.config.security;

import com.bank.shareaccount.global.config.jwt.JwtService;
import com.bank.shareaccount.global.util.CustomHttpServletRequestWrapper;
import com.bank.shareaccount.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
//@Component
public class LoginFilter extends OncePerRequestFilter {

    private static final String CONTENT_TYPE = "application/json";

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("loginFilter 진입");
        if (request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE)) {
            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + request.getContentType());
        }

        String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);

        @SuppressWarnings("unchecked")
        Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);

        String id = usernamePasswordMap.get("id");

        String password = usernamePasswordMap.get("password");
        String dbPassword = userRepository.findById(id).orElseThrow(IllegalArgumentException::new).getPassword();

        if (PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(password, dbPassword)) {
            log.info("패스워드 일치 ");
            String newAccessToken = jwtService.createAccessToken(id);
            String newRefreshToken = jwtService.createRefreshToken();
            jwtService.sendBothToken(response, newAccessToken, newRefreshToken);

            HttpServletRequest requestWrapper = new CustomHttpServletRequestWrapper(request, messageBody);
            filterChain.doFilter(requestWrapper, response);
        }
    }
}
