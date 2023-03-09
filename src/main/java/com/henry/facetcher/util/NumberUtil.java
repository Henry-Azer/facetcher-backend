package com.henry.facetcher.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Henry Azer
 * @since 11/02/2023
 */
@Slf4j
public class NumberUtil {

    public static double positiveRandomNumberUnderOne() {
        log.info("NumberUtil: positiveRandomNumberUnderOne() called");
        Random random = new Random();
        return 0.3 + (0.5) * random.nextDouble();
    }

    public static int randomFiveDigits() {
        log.info("NumberUtil: randomFiveDigits() called");
        Random random = new Random();
        return random.nextInt(100000) + 10000;
    }
}
