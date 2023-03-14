package com.henry.facetcher.service;

import com.henry.facetcher.dao.ConfigValueDao;
import com.henry.facetcher.dto.ConfigValueDto;
import com.henry.facetcher.model.ConfigValue;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.ConfigValueTransformer;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
public interface ConfigValueService extends BaseService<ConfigValue, ConfigValueDto, ConfigValueDao, ConfigValueTransformer> {
}
