package com.henry.facetcher.aop;

import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.dto.UserLogDto;
import com.henry.facetcher.dto.base.response.AuthResponse;
import com.henry.facetcher.enums.UserLogStatus;
import com.henry.facetcher.security.jwt.JWTAuthenticationUtil;
import com.henry.facetcher.service.UserLogService;
import com.henry.facetcher.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.henry.facetcher.constants.FacetcherConstants.LOGGED_IN_ASPECT;
import static com.henry.facetcher.constants.FacetcherConstants.LOGGED_OUT_ASPECT;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class AuthenticationServiceAspect {
    private final UserService userService;
    private final UserLogService userLogService;
    private final JWTAuthenticationUtil authenticationUtil;

    @AfterReturning(pointcut = "execution(* com.henry.facetcher.manager.JWTAuthenticationManager.login(..))", returning = "authResponse")
    public void logAfterUserLogin(AuthResponse authResponse) {
        log.info("AuthenticationServiceAspect: logAfterUserLogin() called");
        userLogService.create(constructUserLogDto(UserLogStatus.LOGIN, LOGGED_IN_ASPECT, userService.findUserByEmail(authenticationUtil.getAccessTokenUserEmail(authResponse.getAccessToken()))));
        log.info("AuthenticationServiceAspect: logAfterUserLogin() ended");
    }

    @AfterReturning(pointcut = "execution(* com.henry.facetcher.manager.JWTAuthenticationManager.logout(..))")
    public void logAfterUserLogout() {
        log.info("AuthenticationServiceAspect: logAfterUserLogout() called");
        userLogService.create(constructUserLogDto(UserLogStatus.LOGOUT, LOGGED_OUT_ASPECT, userService.getCurrentUser()));
        log.info("AuthenticationServiceAspect: logAfterUserLogin() ended");
    }

    private UserLogDto constructUserLogDto(UserLogStatus logStatus, String logMessage, UserDto userDto) {
        log.info("AuthenticationServiceAspect: constructUserLogDto() called");
        UserLogDto userLogDto = new UserLogDto();
        userLogDto.setUser(userDto);
        userLogDto.setLogStatus(logStatus);
        userLogDto.setLogDate(LocalDateTime.now());
        userLogDto.setLogMessage(userLogDto.getUser().getEmail() + logMessage + userLogDto.getLogDate());
        return userLogDto;
    }
}
