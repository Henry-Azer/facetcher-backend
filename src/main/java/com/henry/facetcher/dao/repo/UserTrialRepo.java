package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.UserTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Repository
public interface UserTrialRepo extends JpaRepository<UserTrial, Long> {
    List<UserTrial> findAllByMarkedAsDeletedFalse();
    Optional<UserTrial> findUserTrialByIdAndMarkedAsDeletedFalse(Long userTrailId);
    List<UserTrial> findAllByUserSubmissionIdAndMarkedAsDeletedFalse(Long userSubmissionId);
    List<UserTrial> findAllByExceptionOccurredTrueAndMarkedAsDeletedFalse();
    Long countAllByUserSubmissionId(Long userSubmissionId);
}
