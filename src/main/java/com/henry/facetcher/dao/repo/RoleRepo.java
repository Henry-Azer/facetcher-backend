package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findAllByMarkedAsDeletedFalse();
}
