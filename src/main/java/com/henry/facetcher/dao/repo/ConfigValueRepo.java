package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.ConfigValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Repository
public interface ConfigValueRepo extends JpaRepository<ConfigValue, Long> {
}
