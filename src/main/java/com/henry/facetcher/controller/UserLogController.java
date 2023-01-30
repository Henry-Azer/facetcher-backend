package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.service.UserLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/user-log")
public class UserLogController implements BaseController<UserLogService> {
    private final UserLogService userLogService;

    @Override
    public UserLogService getService() {
        return userLogService;
    }
}
