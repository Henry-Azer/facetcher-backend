package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.model.UserTrial;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.UserTrialMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Component
@AllArgsConstructor
public class UserTrialTransformer implements BaseTransformer<UserTrial, UserTrialDto, UserTrialMapper> {
    private final UserTrialMapper userTrialMapper;

    @Override
    public UserTrialMapper getMapper() {
        return userTrialMapper;
    }
}
