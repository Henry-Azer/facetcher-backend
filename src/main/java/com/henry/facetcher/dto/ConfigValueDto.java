package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import lombok.*;

import javax.persistence.Column;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigValueDto extends BaseDto {
    private Long id;
    private String key;
    private String value;
    private String type;
}
