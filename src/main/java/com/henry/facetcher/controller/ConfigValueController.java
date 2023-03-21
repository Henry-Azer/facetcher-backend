package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.dto.ConfigValueDto;
import com.henry.facetcher.dto.base.response.ApiResponse;
import com.henry.facetcher.service.ConfigValueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/config")
public class ConfigValueController implements BaseController<ConfigValueService> {
    private final ConfigValueService configValueService;

    @Override
    public ConfigValueService getService() {
        return configValueService;
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findConfigValues() {
        log.info("ConfigValueController: findConfigValues() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Config values fetched successfully.", getService().findAll());
    }

    @GetMapping("/find-by-key/{key}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse findConfigValueByKey(@PathVariable String key) {
        log.info("ConfigValueController: findConfigValueByKey() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Config value fetched successfully.", getService().findConfigValueByConfigKey(key));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createConfigValue(@RequestBody ConfigValueDto configValueDto) {
        log.info("ConfigValueController: createConfigValue() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Config value created successfully.", getService().create(configValueDto));
    }

    @PostMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse createConfigValues(@RequestBody List<ConfigValueDto> configValueDtos) {
        log.info("ConfigValueController: createConfigValues() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Config values created successfully.", getService().create(configValueDtos));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse updateConfigValue(@RequestBody ConfigValueDto configValueDto) {
        log.info("ConfigValueController: updateConfigValue() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Config value updated successfully.", getService().update(configValueDto, configValueDto.getId()));
    }
}
