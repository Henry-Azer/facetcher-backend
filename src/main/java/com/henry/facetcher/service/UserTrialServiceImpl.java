package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserTrialDao;
import com.henry.facetcher.dto.ImageDto;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.transformer.UserTrialTransformer;
import com.henry.facetcher.util.FDLProcessor;
import com.henry.facetcher.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserTrialServiceImpl implements UserTrialService {
    private final UserTrialTransformer userTrialTransformer;
    private final UserTrialDao userTrialDao;
    private final ImageService imageService;
    private final JWTAuthenticationManager authenticationManager;

    @Override
    public UserTrialTransformer getTransformer() {
        return userTrialTransformer;
    }

    @Override
    public UserTrialDao getDao() {
        return userTrialDao;
    }

    @Override
    @Transactional
    public UserTrialDto processUserTrial(UserTrialDto userTrialDto) {
        log.info("UserTrialService: processUserTrial() called");
        ImageDto dbInputImage = imageService.create(constructImageDto(userTrialDto.getInputImageFile()));
        userTrialDto.setInputImageId(dbInputImage.getId());
        FDLProcessor.process(dbInputImage);

//        ImageDto dbOutputImage = imageService.create(constructImageDto(FDLProcessor.process(dbInputImage.getImage(), dbInputImage.getName())));
//        userTrialDto.setOutputImageId(dbOutputImage.getId());


        //        return create(userTrialDto);
        return null;
    }

    private ImageDto constructImageDto(MultipartFile inputImage) {
        log.info("UserTrialService: constructImageDto() called");
        ImageDto imageDto = new ImageDto();
        imageDto.setName(StringUtil.getRandomImageName(inputImage.getOriginalFilename()));
        try {
            imageDto.setImage(inputImage.getBytes());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return imageDto;
    }

//    private ImageDto constructImageDto(File inputImage) {
//        log.info("UserTrialService: constructImageDto() called");
//        ImageDto imageDto = new ImageDto();
//        imageDto.setName(StringUtil.getRandomImageName(inputImage.getName()));
//        try {
//            imageDto.setImage(inputImage.getBytes());
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//        return imageDto;
//    }
}
