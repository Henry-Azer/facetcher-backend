package com.henry.facetcher.service;

import com.henry.facetcher.dao.ImageDao;
import com.henry.facetcher.dto.ImageDto;
import com.henry.facetcher.transformer.ImageTransformer;
import com.henry.facetcher.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Override
    public ImageDto constructImageDto(MultipartFile inputImage) {
        log.info("ImageService: constructImageDto() called");
        ImageDto imageDto = new ImageDto();
        imageDto.setName(StringUtil.getRandomImageName(inputImage.getOriginalFilename()));
        try {
            imageDto.setImage(inputImage.getBytes());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return imageDto;
    }

    @Override
    public ImageDto constructImageDto(MultipartFile inputImage, String imageName) {
        log.info("ImageService: constructImageDto() called");
        ImageDto imageDto = new ImageDto();
        imageDto.setName(imageName);
        try {
            imageDto.setImage(inputImage.getBytes());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return imageDto;
    }
}
