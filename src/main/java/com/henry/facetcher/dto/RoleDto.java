package com.henry.facetcher.dto;

import com.henry.facetcher.dto.base.BaseDto;
import lombok.*;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDto {
    private Long id;
    private String name;
    private String description;
}
