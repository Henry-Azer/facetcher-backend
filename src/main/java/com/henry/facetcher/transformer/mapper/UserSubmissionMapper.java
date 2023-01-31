package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.model.UserSubmission;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserSubmissionMapper extends BaseMapper<UserSubmission, UserSubmissionDto> {

    @Override
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "inputImageId", source = "inputImage.id")
    @Mapping(target = "outputImageId", source = "outputImage.id")
    UserSubmissionDto entityToDto(UserSubmission entity);

    @Override
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "inputImage", ignore = true)
    @Mapping(target = "outputImage", ignore = true)
    UserSubmission dtoToEntity(UserSubmissionDto dto);
}
