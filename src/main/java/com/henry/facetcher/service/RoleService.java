package com.henry.facetcher.service;

import com.henry.facetcher.dao.RoleDao;
import com.henry.facetcher.dto.RoleDto;
import com.henry.facetcher.model.Role;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.RoleTransformer;

import java.util.List;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
public interface RoleService extends BaseService<Role, RoleDto, RoleDao, RoleTransformer> {
    List<RoleDto> findAllRoles();
    RoleDto toggleRoleDeletionById(Long roleId);
}
