package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserTrialRepo;
import com.henry.facetcher.model.UserTrial;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Component
public class UserTrialDaoImpl implements UserTrialDao {
    private final UserTrialRepo userTrialRepo;

    public UserTrialDaoImpl(UserTrialRepo userTrialRepo) {
        this.userTrialRepo = userTrialRepo;
    }

    @Override
    public UserTrialRepo getRepository() {
        return userTrialRepo;
    }

    @Override
    public Optional<UserTrial> findById(Long userTrailId) {
        return getRepository().findUserTrialByIdAndMarkedAsDeletedFalse(userTrailId);
    }

    @Override
    public List<UserTrial> findAllUserTrials() {
        return getRepository().findAllByMarkedAsDeletedFalse();
    }

    @Override
    public List<UserTrial> findAllFailedUserTrials() {
        return getRepository().findAllByExceptionOccurredAndMarkedAsDeletedFalse(true);
    }

    @Override
    public List<UserTrial> getAllSucceededUserTrials() {
        return getRepository().findAllByExceptionOccurredAndMarkedAsDeletedFalse(false);
    }

    @Override
    public List<UserTrial> findAllUserTrialsByUserId(Long userId) {
        return getRepository().findAllByUserIdAndMarkedAsDeletedFalse(userId);
    }

    @Override
    public List<UserTrial> findAllUserTrialsByUserSubmissionId(Long userSubmissionId) {
        return getRepository().findAllByUserSubmissionIdAndMarkedAsDeletedFalse(userSubmissionId);
    }

    @Override
    public Long findSucceededUserTrialsCountByUserId(Long userId) {
        return getRepository().countAllByUserIdAndExceptionOccurredAndMarkedAsDeletedFalse(userId, false);
    }

    @Override
    public Long findFailedUserTrialsCountByUserId(Long userId) {
        return getRepository().countAllByUserIdAndExceptionOccurredAndMarkedAsDeletedFalse(userId, true);
    }
}
