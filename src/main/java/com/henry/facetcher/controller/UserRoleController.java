package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.UserRoleDto;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.UserRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user-role")
public class UserRoleController implements BaseController<UserRoleService> {
    private final UserRoleService userRoleService;

    @Override
    public UserRoleService getService() {
        return userRoleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createUserRole(@RequestBody UserRoleDto userRoleDto) {
        log.info("UserRoleController: createUserRole() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Role created successfully.", getService().create(userRoleDto));
    }

    @PutMapping("/{userRoleId}/toggle-deletion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse toggleUserRoleDeletionById(@PathVariable Long userRoleId) {
        log.info("UserRoleController: toggleUserRoleDeletionById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User Role deletion toggled successfully.", getService().toggleUserRoleDeletionById(userRoleId));
    }
}
