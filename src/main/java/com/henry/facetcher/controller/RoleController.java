package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.RoleDto;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.RoleService;
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
@RequestMapping("/api/role")
public class RoleController implements BaseController<RoleService> {
    private final RoleService roleService;

    @Override
    public RoleService getService() {
        return roleService;
    }

    @GetMapping("/find-all-roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse getRoles() {
        log.info("RoleController: getUserRoles() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Roles fetched successfully.", getService().findAllRoles());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createRole(@RequestBody RoleDto roleDto) {
        log.info("RoleController: createRole() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Role created successfully.", getService().create(roleDto));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse updateRole(@RequestBody RoleDto roleDto) {
        log.info("RoleController: updateRole() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Role updated successfully.", getService().update(roleDto, roleDto.getId()));
    }

    @PutMapping("/{roleId}/toggle-deletion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse toggleRoleDeletionById(@PathVariable Long roleId) {
        log.info("RoleController: toggleRoleDeletionById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Role deletion toggled successfully.", getService().toggleRoleDeletionById(roleId));
    }
}
