package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.CloudFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/cloud")
public class CloudFileController implements BaseController<CloudFileService> {
    private final CloudFileService cloudFileService;

    @Override
    public CloudFileService getService() {
        return cloudFileService;
    }

    @GetMapping("/find-by-type/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findCloudAssetsFiles(@PathVariable String type) {
        log.info("CloudFileController: findCloudAssetsFiles() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Cloud files fetched successfully.", getService().findCloudAssetsFiles(type));
    }

    @PostMapping(value = "/upload/type/{type}/name/{name}", consumes = {"multipart/form-data"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse uploadCloudFile(@RequestBody MultipartFile file, @PathVariable String type, @PathVariable String name) {
        log.info("CloudFileController: uploadCloudFile() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Cloud file uploaded successfully.", getService(). uploadCloudFile(file, type, name));
    }
}
