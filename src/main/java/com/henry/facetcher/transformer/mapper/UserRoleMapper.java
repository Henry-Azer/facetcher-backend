package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.UserRoleDto;
import com.henry.facetcher.model.UserRole;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserRoleMapper extends BaseMapper<UserRole, UserRoleDto> {
    @Override
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "roleId", source = "role.id")
    UserRoleDto entityToDto(UserRole entity);

    @Override
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserRole dtoToEntity(UserRoleDto dto);
}
