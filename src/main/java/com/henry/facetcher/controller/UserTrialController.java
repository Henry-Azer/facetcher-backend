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

    @PostMapping(value = "/process", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse processUserTrial(@RequestBody @ModelAttribute UserTrialDto userTrialDto) {
        log.info("UserTrialController: processUserTrial() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Trial processed successfully.", getService().processUserTrial(userTrialDto));
    }
}
