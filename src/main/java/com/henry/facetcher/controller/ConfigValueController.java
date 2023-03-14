package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.service.ConfigValueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/config")
public class ConfigValueController implements BaseController<ConfigValueService> {
    private final ConfigValueService configValueService;

    @Override
    public ConfigValueService getService() {
        return configValueService;
    }
}
