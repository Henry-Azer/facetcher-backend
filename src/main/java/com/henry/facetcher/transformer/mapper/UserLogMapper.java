package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.UserLogDto;
import com.henry.facetcher.model.UserLog;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserLogMapper extends BaseMapper<UserLog, UserLogDto> {
}
