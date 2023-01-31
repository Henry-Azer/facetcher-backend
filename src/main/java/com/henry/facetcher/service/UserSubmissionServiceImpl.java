package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserSubmissionDao;
import com.henry.facetcher.transformer.UserSubmissionTransformer;
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
public class UserSubmissionServiceImpl implements UserSubmissionService {
    private final UserSubmissionTransformer userSubmissionTransformer;
    private final UserSubmissionDao userSubmissionDao;

    @Override
    public UserSubmissionTransformer getTransformer() {
        return userSubmissionTransformer;
    }

    @Override
    public UserSubmissionDao getDao() {
        return userSubmissionDao;
    }
}
