package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.UserSubmissionDto;
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

    @GetMapping("/find-by-id/{userSubmissionId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse getUserSubmissionById(@PathVariable Long userSubmissionId) {
        log.info("UserSubmissionController: getUserSubmissionById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submission fetched successfully.", getService().findById(userSubmissionId));
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findAllUserSubmissions() {
        log.info("UserSubmissionController: findAllUserSubmissions() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submissions fetched successfully.", getService().findAllUserSubmissions());
    }

    @GetMapping("/find-all-by-user-id/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findAllUserSubmissionsByUserId(@PathVariable Long userId) {
        log.info("UserSubmissionController: findAllUserSubmissions() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submissions  fetched successfully.", getService().findAllUserSubmissionsByUserId(userId));
    }

    @GetMapping("/current-user/find-all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse findAllUserSubmissionsByCurrentUser() {
        log.info("UserSubmissionController: findAllUserSubmissionsByCurrentUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submissions fetched successfully.", getService().findAllUserSubmissionsByCurrentUser());
    }

    @GetMapping("/current-user/count")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse getUserSubmissionsCountByCurrentUser() {
        log.info("UserSubmissionController: getUserSubmissionsCountByCurrentUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submissions count fetched successfully.", getService().findUserSubmissionsCountByCurrentUser());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse createOrUpdateUserSubmission(@RequestBody UserSubmissionDto userSubmissionDto) {
        log.info("UserSubmissionController: createOrUpdateUserSubmission() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submission submitted successfully.", getService().createOrUpdate(userSubmissionDto));
    }

    @PutMapping("/{userSubmissionId}/toggle-deletion")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse toggleUserSubmissionDeletionById(@PathVariable Long userSubmissionId) {
        log.info("UserSubmissionController: toggleUserSubmissionDeletionById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Submission deletion toggled successfully.", getService().toggleUserSubmissionDeletionById(userSubmissionId));
    }
}
