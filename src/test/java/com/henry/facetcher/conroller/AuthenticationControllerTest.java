package com.henry.facetcher.conroller;

import com.henry.facetcher.controller.AuthenticationController;
import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.dto.base.request.AuthRequest;
import com.henry.facetcher.dto.base.request.RefreshTokenRequest;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.dto.base.response.AuthResponse;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private JWTAuthenticationManager jwtAuthenticationManager;

    @Mock
    private UserService userService;

    @Test
    public void login() {
        log.info("AuthenticationControllerTest: login() called");
        AuthRequest authRequest = new AuthRequest("test@facetcher.com", "test@facetcher");
        AuthResponse expectedResponse = new AuthResponse();
        when(jwtAuthenticationManager.login(authRequest)).thenReturn(expectedResponse);

        ApiResponse response = authenticationController.login(authRequest);

        assertEquals(true, response.getSuccess());
        assertEquals("User logged in successfully.", response.getMessage());
        assertEquals(expectedResponse, response.getBody());
        log.info("AuthenticationControllerTest: login() ended");
    }

    @Test
    public void currentLoggedUser() {
        log.info("AuthenticationControllerTest: currentLoggedUser() called");
        UserDto expectedResponse = new UserDto();
        when(userService.getCurrentUser()).thenReturn(expectedResponse);

        ApiResponse response = authenticationController.currentLoggedUser();

        assertEquals(true, response.getSuccess());
        assertEquals("Current logged user fetched successfully.", response.getMessage());
        assertEquals(expectedResponse, response.getBody());
        log.info("AuthenticationControllerTest: currentLoggedUser() ended");
    }

    @Test
    public void refreshToken() {
        log.info("AuthenticationControllerTest: refreshToken() called");
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("test@test.com", "refreshToken");
        AuthResponse expectedResponse = new AuthResponse();
        when(jwtAuthenticationManager.refreshToken(refreshTokenRequest)).thenReturn(expectedResponse);

        ApiResponse response = authenticationController.refreshToken(refreshTokenRequest);

        assertEquals(true, response.getSuccess());
        assertEquals("Token Refreshed successfully.", response.getMessage());
        assertEquals(expectedResponse, response.getBody());
        log.info("AuthenticationControllerTest: refreshToken() ended");
    }

    @Test
    public void logout() {
        log.info("AuthenticationControllerTest: logout() called");
        when(jwtAuthenticationManager.logout()).thenReturn(true);

        ApiResponse response = authenticationController.logout();

        assertEquals(true, response.getSuccess());
        assertEquals("User logged out successfully.", response.getMessage());
        assertEquals(true, response.getBody());
        log.info("AuthenticationControllerTest: logout() ended");
    }
}
