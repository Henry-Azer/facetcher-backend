package com.henry.facetcher.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 03/02/2023
 */
@Slf4j
@Component
public class StringUtil {

    public static String getRandomImageName(String imageName) {
        log.info("StringUtil: getRandomImageName() called");
        return "IMG-" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "-" + NumberUtil.randomFiveDigits() + getFileExtension(imageName);
    }

    public static String getFileExtension(String fileName) {
        log.info("StringUtil: getFileExtension() called");
        if (fileName == null) return null;
        int index = fileName.lastIndexOf(".");
        if (index == -1) return null;
        return fileName.substring(index);
    }
}
