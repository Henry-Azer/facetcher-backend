package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.model.UserTrial;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserTrialMapper extends BaseMapper<UserTrial, UserTrialDto> {
}
