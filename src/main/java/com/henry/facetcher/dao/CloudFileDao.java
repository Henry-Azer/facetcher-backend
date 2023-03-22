package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.CloudFileRepo;
import com.henry.facetcher.model.CloudFile;

import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
public interface CloudFileDao extends BaseDao<CloudFile, CloudFileRepo> {
    List<CloudFile> findCloudAssetsFiles(String type);
    Optional<CloudFile> findCloudFileByFileName(String fileName);
}
