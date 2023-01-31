package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserSubmissionRepo;
import org.springframework.stereotype.Component;

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
}
