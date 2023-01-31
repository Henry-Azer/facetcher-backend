package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserTrialDao;
import com.henry.facetcher.transformer.UserTrialTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserTrialServiceImpl implements UserTrialService {
    private final UserTrialTransformer userTrialTransformer;
    private final UserTrialDao userTrialDao;

    @Override
    public UserTrialTransformer getTransformer() {
        return userTrialTransformer;
    }

    @Override
    public UserTrialDao getDao() {
        return userTrialDao;
    }
}
