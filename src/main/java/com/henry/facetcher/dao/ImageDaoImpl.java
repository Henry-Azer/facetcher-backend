package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.ImageRepo;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Component
public class ImageDaoImpl implements ImageDao {
    private final ImageRepo imageRepo;

    public ImageDaoImpl(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public ImageRepo getRepository() {
        return imageRepo;
    }
}
