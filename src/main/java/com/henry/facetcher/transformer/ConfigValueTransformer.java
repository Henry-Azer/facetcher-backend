package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.ConfigValueDto;
import com.henry.facetcher.model.ConfigValue;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.ConfigValueMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Component
@AllArgsConstructor
public class ConfigValueTransformer implements BaseTransformer<ConfigValue, ConfigValueDto, ConfigValueMapper> {
    private final ConfigValueMapper configValueMapper;

    @Override
    public ConfigValueMapper getMapper() {
        return configValueMapper;
    }
}
