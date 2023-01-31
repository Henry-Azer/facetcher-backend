package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSubmissionDto extends BaseDto {
    private Long id;
    private UserDto user;
    private Long userId;
    private ImageDto inputImage;
    private Long inputImageId;
    private ImageDto outputImage;
    private Long outputImageId;
    private String title;
    private String description;
    private Long trialCount;
    private LocalDateTime submissionDate;
    private String submissionMessage;
}
