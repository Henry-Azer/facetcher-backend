package com.henry.facetcher.transformer.mapper;

import com.henry.facetcher.dto.RoleDto;
import com.henry.facetcher.model.Role;
import com.henry.facetcher.transformer.mapper.base.BaseMapper;
import com.henry.facetcher.transformer.mapper.base.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface RoleMapper extends BaseMapper<Role, RoleDto> {
}
