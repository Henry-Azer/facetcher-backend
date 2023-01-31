package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.UserSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Repository
public interface UserSubmissionRepo extends JpaRepository<UserSubmission, Long> {
    List<UserSubmission> findAllByUserIdAndMarkedAsDeletedFalse(Long userId);
}
