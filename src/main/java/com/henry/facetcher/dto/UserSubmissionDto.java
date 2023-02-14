package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import com.henry.facetcher.enums.Gender;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private Long userId;
    private Long inputImageId;
    private ImageDto inputImage;
    private Long outputImageId;
    private ImageDto outputImage;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String title;
    private String description;
    private Long trialCount;
    private LocalDateTime submissionDate;
    private String submissionMessage;
    private Boolean submitted = false;
}
