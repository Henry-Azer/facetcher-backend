package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.UserTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Repository
public interface UserTrialRepo extends JpaRepository<UserTrial, Long> {
    Long countAllByTitle(String title);
}
