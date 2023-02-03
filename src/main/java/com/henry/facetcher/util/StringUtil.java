package com.henry.facetcher.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Henry Azer
 * @since 03/02/2023
 */
@Slf4j
@Component
public class StringUtil {

    public static String getRandomImageName(String imageName) {
        log.info("StringUtil: getRandomImageName() called");
        Random random = new Random();
        return "IMG-" + new SimpleDateFormat("dd-MM-yyyy").format(new Date())
                + "-" + (random.nextInt(100000) + 10000) + getFileExtension(imageName);
    }

    public static String getFileExtension(String fileName) {
        log.info("StringUtil: getFileExtension() called");
        if (fileName == null) return null;
        int index = fileName.lastIndexOf(".");
        if (index == -1) return null;
        return fileName.substring(index);
    }
}
