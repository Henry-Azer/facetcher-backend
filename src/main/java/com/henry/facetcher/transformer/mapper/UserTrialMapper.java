package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.UserTrialDto;
import com.henry.facetcher.model.UserTrial;
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
public interface UserTrialMapper extends BaseMapper<UserTrial, UserTrialDto> {
    @Override
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "inputImageId", source = "inputImage.id")
    @Mapping(target = "outputImageId", source = "outputImage.id")
    UserTrialDto entityToDto(UserTrial entity);
}
