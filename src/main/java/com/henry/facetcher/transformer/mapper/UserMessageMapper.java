package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.model.UserMessage;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserMessageMapper extends BaseMapper<UserMessage, UserMessageDto> {
}
