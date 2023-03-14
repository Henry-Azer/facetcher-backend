package com.henry.facetcher.service;

import com.henry.facetcher.dao.CloudFileDao;
import com.henry.facetcher.transformer.CloudFileTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class CloudFileServiceImpl implements CloudFileService {
    private final CloudFileTransformer cloudFileTransformer;
    private final CloudFileDao cloudFileDao;

    @Override
    public CloudFileTransformer getTransformer() {
        return cloudFileTransformer;
    }

    @Override
    public CloudFileDao getDao() {
        return cloudFileDao;
    }
}
