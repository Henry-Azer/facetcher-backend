package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.UserRoleDto;
import com.henry.facetcher.model.UserRole;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Component
@AllArgsConstructor
public class UserRoleTransformer implements BaseTransformer<UserRole, UserRoleDto, UserRoleMapper> {
    private final UserRoleMapper userRoleMapper;

    @Override
    public UserRoleMapper getMapper() {
        return userRoleMapper;
    }
}
