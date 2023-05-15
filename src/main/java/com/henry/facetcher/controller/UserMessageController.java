package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.UserMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user-message")
public class UserMessageController implements BaseController<UserMessageService> {
    private final UserMessageService userMessageService;

    @Override
    public UserMessageService getService() {
        return userMessageService;
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findAllUserMessages() {
        log.info("UserMessageController: findAllUserMessages() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Messages fetched successfully.", getService().findAllUserMessages());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse createUserMessage(@RequestBody UserMessageDto userMessageDto) {
        log.info("UserSubmissionController: createUserMessage() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Message created successfully.", getService().create(userMessageDto));
    }
}
