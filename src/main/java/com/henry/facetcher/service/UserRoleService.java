package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserRoleDao;
import com.henry.facetcher.dto.UserRoleDto;
import com.henry.facetcher.model.UserRole;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserRoleTransformer;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
public interface UserRoleService extends BaseService<UserRole, UserRoleDto, UserRoleDao, UserRoleTransformer> {
    UserRoleDto toggleUserRoleDeletionById(Long userRoleId);
}
