package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.UserLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user-log")
public class UserLogController implements BaseController<UserLogService> {
    private final UserLogService userLogService;

    @Override
    public UserLogService getService() {
        return userLogService;
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ApiResponse findAllUserLogs() {
        log.info("UserLogController: findAllUserLogs() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Users Logs fetched successfully.", getService().findAll());
    }
}
