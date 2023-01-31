package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserTrialRepo;
import org.springframework.stereotype.Component;

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
}
