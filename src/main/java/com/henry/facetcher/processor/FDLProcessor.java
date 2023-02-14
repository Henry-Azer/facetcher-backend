package com.henry.facetcher.processor;

import com.henry.facetcher.dto.UserTrialDto;

/**
 * @author Henry Azer
 * @since 12/02/2023
 */
public interface FDLProcessor {
    void process(UserTrialDto userTrialDto);
}
