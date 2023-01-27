package com.henry.facetcher.manager;

import com.henry.facetcher.dto.base.request.AuthRequest;
import com.henry.facetcher.dto.base.request.RefreshTokenRequest;
import com.henry.facetcher.dto.base.response.AuthResponse;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public interface JWTAuthenticationManager {
    AuthResponse login(AuthRequest authRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean logout();

    String getCurrentUserEmail();
}
