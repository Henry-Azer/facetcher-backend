package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import com.henry.facetcher.enums.Gender;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
public class UserTrialDto extends BaseDto {
    private Long id;
    private UserDto user;
    private Long userId;
    private MultipartFile inputImageFile;
    private ImageDto inputImage;
    private Long inputImageId;
    private MultipartFile outputImageFile;
    private ImageDto outputImage;
    private Long outputImageId;
    private Gender gender;
    private String title;
    private String description;
    private LocalDateTime trialDate;
    private String trailMessage;
}
