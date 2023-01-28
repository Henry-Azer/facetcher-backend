package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.RoleDto;
import com.henry.facetcher.model.Role;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Component
@AllArgsConstructor
public class RoleTransformer implements BaseTransformer<Role, RoleDto, RoleMapper> {
    private final RoleMapper roleMapper;

    @Override
    public RoleMapper getMapper() {
        return roleMapper;
    }
}
