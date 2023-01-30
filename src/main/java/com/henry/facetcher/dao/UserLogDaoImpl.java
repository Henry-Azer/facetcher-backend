package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserLogRepo;
import com.henry.facetcher.enums.UserLogStatus;
import com.henry.facetcher.model.UserLog;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Component
public class UserLogDaoImpl implements UserLogDao {
    private final UserLogRepo userLogRepo;

    public UserLogDaoImpl(UserLogRepo userLogRepo) {
        this.userLogRepo = userLogRepo;
    }

    @Override
    public UserLogRepo getRepository() {
        return userLogRepo;
    }

    @Override
    public Optional<UserLog> findLastCurrentUserLogByUserIdAndStatus(Long userId, UserLogStatus logStatus) {
        return getRepository().findDistinctTopByUserIdAndLogStatusAndMarkedAsDeletedFalseOrderByLogDateDesc(userId, logStatus);
    }
}
