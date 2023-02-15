package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import lombok.*;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserMessageDto extends BaseDto {
    private Long id;
    private UserDto user;
    private Long userId;
    private String title;
    private String message;
}
