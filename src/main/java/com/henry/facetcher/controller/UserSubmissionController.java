package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.UserSubmissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user-submission")
public class UserSubmissionController implements BaseController<UserSubmissionService> {
    private final UserSubmissionService userSubmissionService;

    @Override
    public UserSubmissionService getService() {
        return userSubmissionService;
    }

    @GetMapping("/find-all-user-submissions")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse findAllUserSubmissionsByCurrentUser() {
        log.info("AuthenticationController: findAllUserSubmissionsByCurrentUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submissions fetched successfully.", getService().findAllUserSubmissionsByCurrentUser());
    }

    @PostMapping("/submit/{userTrialId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse submitUserSubmissionByUserTrialId(@PathVariable Long userTrialId) {
        log.info("UserSubmissionController: submitUserSubmissionByTrialId() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submission submitted successfully.", getService().submitUserSubmissionByUserTrialId(userTrialId));
    }

    @PutMapping("/{userSubmissionId}/toggle-deletion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse toggleUserSubmissionDeletionById(@PathVariable Long userSubmissionId) {
        log.info("UserSubmissionController: toggleUserSubmissionDeletionById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submission deletion toggled successfully.", getService().toggleUserSubmissionDeletionById(userSubmissionId));
    }
}