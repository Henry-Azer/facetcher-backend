package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.UserSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Repository
public interface UserSubmissionRepo extends JpaRepository<UserSubmission, Long> {
}
