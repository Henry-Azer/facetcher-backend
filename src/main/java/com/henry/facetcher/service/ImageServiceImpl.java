package com.henry.facetcher.service;

import com.henry.facetcher.dao.ImageDao;
import com.henry.facetcher.dto.ImageDto;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.storage.StorageManager;
import com.henry.facetcher.transformer.ImageTransformer;
import com.henry.facetcher.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.henry.facetcher.constants.FacetcherConstants.FII_BUCKET;
import static com.henry.facetcher.constants.FacetcherConstants.FII_CDN;

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
    private final StorageManager storageService;
    private final JWTAuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public ImageTransformer getTransformer() {
        return imageTransformer;
    }

    @Override
    public ImageDao getDao() {
        return imageDao;
    }

    @Override
    public ImageDto constructImageDto(MultipartFile image) {
        log.info("ImageService: constructImageDto() called");
        ImageDto imageDto = new ImageDto();
        uploadImage(image, imageDto);
        return imageDto;
    }

    @Override
    public ImageDto constructImageDto(String imageName, String url) {
        log.info("ImageService: constructImageDto() called");
        ImageDto imageDto = new ImageDto();
        imageDto.setName(imageName);
        imageDto.setImageUrl(url);
        return imageDto;
    }

    private void uploadImage(MultipartFile inputImage, ImageDto imageDto) {
        log.info("ImageService: uploadImage() called");
        imageDto.setName(StringUtil.getRandomImageName(inputImage.getOriginalFilename(),
                userService.findUserByEmail(authenticationManager.getCurrentUserEmail()).getId().toString()));
        imageDto.setImageUrl(storageService.uploadFile(inputImage, imageDto.getName(), FII_CDN,FII_BUCKET));
    }
}
