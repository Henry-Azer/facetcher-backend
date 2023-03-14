package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.CloudFileRepo;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Component
public class CloudFileDaoImpl implements CloudFileDao {
    private final CloudFileRepo cloudFileRepo;

    public CloudFileDaoImpl(CloudFileRepo cloudFileRepo) {
        this.cloudFileRepo = cloudFileRepo;
    }

    @Override
    public CloudFileRepo getRepository() {
        return cloudFileRepo;
    }
}
