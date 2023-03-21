package com.henry.facetcher.service;

import com.henry.facetcher.dao.ImageDao;
import com.henry.facetcher.dto.ImageDto;
import com.henry.facetcher.model.Image;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.ImageTransformer;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
public interface ImageService extends BaseService<Image, ImageDto, ImageDao, ImageTransformer> {
    ImageDto constructImageDto(MultipartFile image, String bucket, String cdn);
    ImageDto constructImageDto(String imageName, String url);
}