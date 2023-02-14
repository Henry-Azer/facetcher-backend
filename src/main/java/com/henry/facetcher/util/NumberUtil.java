package com.henry.facetcher.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Henry Azer
 * @since 11/02/2023
 */
@Component
public class NumberUtil {

    public static double positiveRandomNumberUnderOne() {
        Random random = new Random();
        return 0.3 + (0.5) * random.nextDouble();
    }

    public static int randomFiveDigits() {
        Random random = new Random();
        return random.nextInt(100000) + 10000;
    }
}
