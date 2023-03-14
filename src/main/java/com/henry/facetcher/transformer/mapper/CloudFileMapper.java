package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.CloudFileDto;
import com.henry.facetcher.model.CloudFile;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface CloudFileMapper extends BaseMapper<CloudFile, CloudFileDto> {
}
