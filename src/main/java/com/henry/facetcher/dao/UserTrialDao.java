package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.UserTrialRepo;
import com.henry.facetcher.model.UserTrial;

import java.util.List;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
public interface UserTrialDao extends BaseDao<UserTrial, UserTrialRepo> {
    List<UserTrial> findAllUserTrials();
    List<UserTrial> findAllFailedUserTrials();
    List<UserTrial> findAllSucceededUserTrials();
    List<UserTrial> findAllUserTrialsByUserId(Long userId);
    List<UserTrial> findAllUserTrialsByUserSubmissionId(Long userSubmissionId);
    Long findSucceededUserTrialsCountByUserId(Long userId);
    Long findFailedUserTrialsCountByUserId(Long userId);
}
