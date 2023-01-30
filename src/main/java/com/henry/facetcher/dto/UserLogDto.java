package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import com.henry.facetcher.enums.UserLogStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLogDto extends BaseDto {
    private Long id;
    private UserDto user;
    @Enumerated(EnumType.STRING)
    private UserLogStatus logStatus;
    private Long logCount;
    private LocalDateTime logDate;
    private String logMessage;
}
