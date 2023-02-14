package com.henry.facetcher.enums;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public enum Gender {
    MALE("MALE", 1), FEMALE("FEMALE", 0);

    private final String gender;
    private final Integer value;

    Gender(String gender, Integer value) {
        this.gender = gender;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getGender() {
        return gender;
    }
}
