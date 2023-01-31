package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserSubmissionRepo;
import com.henry.facetcher.model.UserSubmission;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<UserSubmission> findAllUserSubmissionsByUserId(Long userId) {
        return getRepository().findAllByUserIdAndMarkedAsDeletedFalse(userId);
    }
}
