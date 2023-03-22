package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.CloudFileRepo;
import com.henry.facetcher.model.CloudFile;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<CloudFile> findCloudAssetsFiles(String type) {
        return getRepository().findAllByTypeAndMarkedAsDeletedFalse(type);
    }
}
