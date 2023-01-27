package com.henry.facetcher.controller.base;

import com.henry.facetcher.service.base.BaseService;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
public interface BaseController<Service extends BaseService> {
    Service getService();
}
