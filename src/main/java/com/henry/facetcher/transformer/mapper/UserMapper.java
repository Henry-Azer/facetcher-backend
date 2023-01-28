package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.model.User;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UserRoleMapper.class}, config = GenericMapperConfiguration.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
