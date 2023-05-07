package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.UserSubmissionRepo;
import com.henry.facetcher.model.UserSubmission;

import java.util.List;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
public interface UserSubmissionDao extends BaseDao<UserSubmission, UserSubmissionRepo> {
    List<UserSubmission> findAllUserSubmissions();
    List<UserSubmission> findAllUserSubmissionsByUserId(Long userId);
    Long countUserSubmittedSubmissionsByUserId(Long userId);
}
