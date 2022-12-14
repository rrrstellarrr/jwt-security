package com.demo.project.jwtsecurity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    private SecurityUtil() {}

    // SecurityContext의 Authentication 객체를 이용해 username을 반환해주는 유틸성 메소드
    public static Optional<String> getCurrentUsername() {
        // SecurityContext에 Authentication 객체가 저장되는 시점 :
        // JwtFilter 클래스의 doFilter() 메소드에서 Request가 들어오는 시점에 SecurityContext에 Authentication 객체를 저장하게 된다.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
}
