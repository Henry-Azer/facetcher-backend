package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserSubmissionRepo;
import com.henry.facetcher.model.UserSubmission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Component
public class UserSubmissionDaoImpl implements UserSubmissionDao {
    private final UserSubmissionRepo userSubmissionRepo;

    public UserSubmissionDaoImpl(UserSubmissionRepo userSubmissionRepo) {
        this.userSubmissionRepo = userSubmissionRepo;
    }

    @Override
    public UserSubmissionRepo getRepository() {
        return userSubmissionRepo;
    }

    @Override
    public Optional<UserSubmission> findById(Long userSubmissionId) {
        return getRepository().findUserSubmissionByIdAndMarkedAsDeletedFalse(userSubmissionId);
    }

    @Override
    public List<UserSubmission> findAllUserSubmissions() {
        return getRepository().findAllByMarkedAsDeletedFalse();
    }

    @Override
    public List<UserSubmission> findAllUserSubmissionsByUserId(Long userId) {
        return getRepository().findAllByUserIdAndMarkedAsDeletedFalse(userId);
    }

    @Override
    public Long countUserSubmittedSubmissionsByUserId(Long userId) {
        return getRepository().countByUserIdAndSubmittedTrueAndMarkedAsDeletedFalse(userId);
    }
}
