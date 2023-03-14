package com.henry.facetcher.service;

import com.henry.facetcher.dao.ConfigValueDao;
import com.henry.facetcher.transformer.ConfigValueTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConfigValueServiceImpl implements ConfigValueService {
    private final ConfigValueTransformer configValueTransformer;
    private final ConfigValueDao configValueDao;

    @Override
    public ConfigValueTransformer getTransformer() {
        return configValueTransformer;
    }

    @Override
    public ConfigValueDao getDao() {
        return configValueDao;
    }
}
