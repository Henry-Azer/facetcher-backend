package com.henry.facetcher.enums;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
public enum UserLogStatus {
    LOGIN("LOGIN"), LOGOUT("LOGOUT");

    private final String logStatus;

    UserLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }
}
