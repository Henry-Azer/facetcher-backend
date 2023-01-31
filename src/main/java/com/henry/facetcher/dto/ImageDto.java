package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import lombok.*;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImageDto extends BaseDto {
    private Long id;
    private String name;
    private byte[] image;
}
