package com.henry.facetcher.service;

import com.henry.facetcher.dao.ImageDao;
import com.henry.facetcher.transformer.ImageTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageTransformer imageTransformer;
    private final ImageDao imageDao;

    @Override
    public ImageTransformer getTransformer() {
        return imageTransformer;
    }

    @Override
    public ImageDao getDao() {
        return imageDao;
    }
}
