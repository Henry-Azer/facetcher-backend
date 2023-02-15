package com.henry.facetcher.dto.base.pagination;

import com.henry.facetcher.dto.base.request.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class FilterPaginationRequest<Filtration> extends PaginationRequest {
    private Filtration criteria;
    private Boolean deletedRecords;
}