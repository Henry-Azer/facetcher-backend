package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserTrialDao;
import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.model.UserTrial;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserTrialTransformer;

import java.util.List;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
public interface UserTrialService extends BaseService<UserTrial, UserTrialDto, UserTrialDao, UserTrialTransformer> {
    List<UserTrialDto> findAllUserTrials();
    List<UserTrialDto> findAllFailedUserTrials();
    List<UserTrialDto> findAllSucceededUserTrials();
    List<UserTrialDto> findAllUserTrialsByUserId(Long userId);
    List<UserTrialDto> findAllUserTrialsByUserSubmissionId(Long userSubmissionId);
    Long findSucceededUserTrialsCountByUserId(Long userId);
    Long findFailedUserTrialsCountByUserId(Long userId);
    UserTrialDto processUserTrial(UserTrialDto userTrialDto);
    UserSubmissionDto submitUserTrialById(Long userTrialId);
}
