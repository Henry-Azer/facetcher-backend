package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserTrialDao;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.model.UserTrial;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserTrialTransformer;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
public interface UserTrialService extends BaseService<UserTrial, UserTrialDto, UserTrialDao, UserTrialTransformer> {
    UserTrialDto processUserTrial(UserTrialDto userTrialDto);
}
