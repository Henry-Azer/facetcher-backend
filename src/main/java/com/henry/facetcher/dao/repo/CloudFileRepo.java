package com.henry.facetcher.dao.repo;

import com.henry.facetcher.model.CloudFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Repository
public interface CloudFileRepo extends JpaRepository<CloudFile, Long> {
}
