package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.UserLogRepo;
import com.henry.facetcher.enums.UserLogStatus;
import com.henry.facetcher.model.UserLog;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
public interface UserLogDao extends BaseDao<UserLog, UserLogRepo> {
    Optional<UserLog> findLastCurrentUserLogByUserIdAndStatus(Long userId, UserLogStatus logStatus);
}
