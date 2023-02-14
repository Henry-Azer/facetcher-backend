package com.henry.facetcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.henry.facetcher.dto.base.BaseDto;
import com.henry.facetcher.enums.Gender;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
    private Long userId;
    private Long userSubmissionId;
    @JsonIgnore
    private UserSubmissionDto userSubmission;
    private Long inputImageId;
    private ImageDto inputImage;
    @JsonIgnore
    private MultipartFile inputImageFile;
    private Long outputImageId;
    private ImageDto outputImage;
    @JsonIgnore
    private MultipartFile outputImageFile;
    @JsonIgnore
    private List<String> processProperties;
    private Boolean exceptionOccurred;
    private String exceptionMessage;
    private String imageProperties;
    private Gender gender;
    private String title;
    private String description;
    private LocalDateTime trialDate;
    private String trailMessage;
}
