package com.henry.facetcher.util;

import com.henry.facetcher.dto.ImageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.henry.facetcher.constants.FacetcherMessageConstants.INPUT_IMAGE_PATH;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@Component
public class FDLProcessor {

    public static byte[] process(ImageDto inputImageDto) {
        log.info("FDLProcessor: process() called");
        File inputImageFile = saveImageToFDL(inputImageDto.getImage(), inputImageDto.getName());
        return null;
    }

    private static File saveImageToFDL(byte[] imageBytes, String imageName) {
        log.info("FDLProcessor: saveImageToFDL() called");
        File imageFile = new File(INPUT_IMAGE_PATH + imageName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            fileOutputStream.write(imageBytes);
            fileOutputStream.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return imageFile;
    }
}
