package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    Boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}
