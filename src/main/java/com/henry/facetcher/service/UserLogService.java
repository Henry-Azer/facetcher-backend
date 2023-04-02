package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserLogDao;
import com.henry.facetcher.dto.UserLogDto;
import com.henry.facetcher.model.UserLog;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserLogTransformer;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
public interface UserLogService extends BaseService<UserLog, UserLogDto, UserLogDao, UserLogTransformer> {
}
