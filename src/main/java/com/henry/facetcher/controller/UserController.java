package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.dto.UserPasswordDto;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController implements BaseController<UserService> {
    private final UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }

    @GetMapping("/find-all-genders")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse getUserGenders() {
        log.info("UserController: getUserGenders() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User genders fetched successfully.", getService().getUserGenders());
    }

    @GetMapping("/find-all-martial-statuses")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse getUserMartialStatuses() {
        log.info("UserController: getUserMartialStatuses() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User martial status fetched successfully.", getService().getUserMartialStatuses());
    }

    @GetMapping("/find-is-email-exists/{email}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse checkIsEmailAlreadyExists(@PathVariable String email) {
        log.info("UserController: checkIsEmailAlreadyExists() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Email existence checked successfully.", getService().isUserExistsByEmail(email));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createUser(@RequestBody UserDto userDto) {
        log.info("UserController: createUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User created successfully.", getService().create(userDto));
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse updateUser(@RequestBody UserDto userDto) {
        log.info("UserController: updateUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User updated successfully.", getService().update(userDto, userDto.getId()));
    }

    @PutMapping("/{userId}/toggle-deletion")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse toggleUserDeletionById(@PathVariable Long userId) {
        log.info("UserController: toggleUserDeletionById() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User deletion toggled successfully.", getService().toggleUserDeletionById(userId));
    }

    @PutMapping("/update-password")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse updateUserPassword(@RequestBody UserPasswordDto userPasswordDto) {
        log.info("UserController: updateUserPassword() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User password updated successfully.", getService().updateUserPassword(userPasswordDto));
    }

    @PostMapping(value = "/profile-picture", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse uploadUserProfilePicture(@RequestBody MultipartFile photo) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User profile picture uploaded successfully.", getService().setUserProfilePicture(photo));
    }

    @DeleteMapping(value = "/profile-picture")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ApiResponse removeUserProfilePicture() {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User profile picture deleted successfully.", getService().removeUserProfilePicture());
    }
}
