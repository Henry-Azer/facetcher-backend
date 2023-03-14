package com.henry.facetcher.controller;

import com.henry.facetcher.controller.base.BaseController;
import com.henry.facetcher.service.CloudFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/cloud")
public class CloudFileController implements BaseController<CloudFileService> {
    private final CloudFileService cloudFileService;

    @Override
    public CloudFileService getService() {
        return cloudFileService;
    }
}
