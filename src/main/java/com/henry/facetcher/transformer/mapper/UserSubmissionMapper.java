package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.model.UserSubmission;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserSubmissionMapper extends BaseMapper<UserSubmission, UserSubmissionDto> {
}
