package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.UserTrialService;
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
@RequestMapping("/api/user-trial")
public class UserTrialController implements BaseController<UserTrialService> {
    private final UserTrialService userTrialService;

    @Override
    public UserTrialService getService() {
        return userTrialService;
    }

    @GetMapping("/find-by-id/{userTrialId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse getUserTrialById(@PathVariable Long userTrialId) {
        log.info("UserTrialController: getUserTrialById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trial fetched successfully.", getService().findById(userTrialId));
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findAllUserTrials() {
        log.info("UserTrialController: findAllUserTrials() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials fetched successfully.", getService().findAllUserTrials());
    }

    @GetMapping("/find-all-failed")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse getAllFailedUserTrials() {
        log.info("UserTrialController: getAllFailedUserTrials() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials failed fetched successfully.", getService().findAllFailedUserTrials());
    }

    @GetMapping("/find-all-succeeded")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse getAllSucceededUserTrials() {
        log.info("UserTrialController: getAllSucceededUserTrials() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials succeeded fetched successfully.", getService().getAllSucceededUserTrials());
    }

    @GetMapping("/find-all-by-submission-id/{submissionId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findAllUserTrialsBySubmissionId(@PathVariable Long submissionId) {
        log.info("UserTrialController: findAllUserTrialsBySubmissionId() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials fetched successfully.", getService().findAllUserTrialsByUserSubmissionId(submissionId));
    }

    @GetMapping("/current-user/find-all")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse findAllUserTrialsByCurrentUser() {
        log.info("UserTrialController: findAllUserTrialsByCurrentUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials fetched successfully.", getService().findAllUserTrialsByCurrentUser());
    }

    @GetMapping("/current-user/count-succeeded")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse findSucceededUserTrialsCountByCurrentUser() {
        log.info("UserTrialController: findSucceededUserTrialsCountByCurrentUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials succeeded count fetched successfully.", getService().findSucceededUserTrialsCountByCurrentUser());
    }

    @GetMapping("/current-user/count-failed")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse findFailedUserTrialsCountByCurrentUser() {
        log.info("UserTrialController: getUserSubmissionsCountByCurrentUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trials failed count fetched successfully.", getService().findFailedUserTrialsCountByCurrentUser());
    }

    @PostMapping(value = "/process", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse processUserTrial(@RequestBody @ModelAttribute UserTrialDto userTrialDto) {
        log.info("UserTrialController: processUserTrial() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trial processed successfully.", getService().processUserTrial(userTrialDto));
    }

    @PutMapping("/submit/{userTrialId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse submitUserTrialById(@PathVariable Long userTrialId) {
        log.info("UserTrialController: submitUserTrialById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trial submitted successfully.", getService().submitUserTrialById(userTrialId));
    }
}
