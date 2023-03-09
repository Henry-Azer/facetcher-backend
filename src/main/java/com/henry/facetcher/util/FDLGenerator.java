package com.henry.facetcher.util;

import com.henry.facetcher.enums.Gender;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry Azer
 * @since 09/03/2023
 */
@Slf4j
public class FDLGenerator {

    public static List<String> generateFDLImageProperties(String inputImageURL, Gender gender) {
        log.info("FDLGenerator: generateFDLImageProperties() called");
        List<String> properties = new ArrayList<>();
        properties.add(inputImageURL);
        properties.addAll(generateFDLParams());
        properties.add(gender.getValue().toString());
        return properties;
    }

    public static List<String> generateFDLParams() {
        log.info("FDLGenerator: generateFDLParams() called");
        List<String> params = new ArrayList<>();
        for (int i = 0; i < 5; i++) params.add(String.format("%.2f", NumberUtil.positiveRandomNumberUnderOne()));
        return params;
    }
}
