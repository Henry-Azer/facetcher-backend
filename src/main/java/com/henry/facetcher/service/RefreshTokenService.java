package com.henry.facetcher.service;


import com.henry.facetcher.dto.base.request.RefreshTokenRequest;
import com.henry.facetcher.entity.RefreshToken;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
public interface RefreshTokenService {
    RefreshToken findRefreshTokenByRefreshToken(String refreshToken);

    RefreshToken createRefreshToken(String email);

    RefreshToken refreshToken(RefreshTokenRequest refreshTokenRequest);

    Boolean deleteRefreshToken(String email);
}
