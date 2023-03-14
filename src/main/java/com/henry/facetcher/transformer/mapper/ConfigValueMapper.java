package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.ConfigValueDto;
import com.henry.facetcher.model.ConfigValue;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface ConfigValueMapper extends BaseMapper<ConfigValue, ConfigValueDto> {
}
