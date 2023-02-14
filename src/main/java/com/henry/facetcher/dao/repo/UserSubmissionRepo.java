package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.UserSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Repository
public interface UserSubmissionRepo extends JpaRepository<UserSubmission, Long> {
    List<UserSubmission> findAllByMarkedAsDeletedFalse();
    List<UserSubmission> findAllByUserIdAndMarkedAsDeletedFalse(Long userId);
    Optional<UserSubmission> findUserSubmissionByIdAndMarkedAsDeletedFalse(Long userSubmissionId);
    Long countByUserIdAndMarkedAsDeletedFalse(Long userId);
}
