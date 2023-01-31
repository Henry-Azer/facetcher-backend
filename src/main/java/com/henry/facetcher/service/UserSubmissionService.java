package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserSubmissionDao;
import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.model.UserSubmission;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserSubmissionTransformer;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
public interface UserSubmissionService extends BaseService<UserSubmission, UserSubmissionDto, UserSubmissionDao, UserSubmissionTransformer> {
}