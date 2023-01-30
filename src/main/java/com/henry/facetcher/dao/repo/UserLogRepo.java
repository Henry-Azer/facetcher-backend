package com.henry.facetcher.dao.repo;

import com.henry.facetcher.enums.UserLogStatus;
import com.henry.facetcher.model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Repository
public interface UserLogRepo extends JpaRepository<UserLog, Long> {
    Optional<UserLog> findDistinctTopByUserIdAndLogStatusAndMarkedAsDeletedFalseOrderByLogDateDesc(Long userId, UserLogStatus logStatus);
}
