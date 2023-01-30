package com.henry.facetcher.dto;

import lombok.*;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggingDto {
    private UserLogDto loginDto;
    private UserLogDto logoutDto;
}
