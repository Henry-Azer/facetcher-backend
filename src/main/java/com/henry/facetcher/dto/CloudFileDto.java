package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import lombok.*;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CloudFileDto extends BaseDto {
    private Long id;
    private String type;
    private String fileName;
    private String bucketName;
    private String cdnCode;
    private String fileURL;
}
