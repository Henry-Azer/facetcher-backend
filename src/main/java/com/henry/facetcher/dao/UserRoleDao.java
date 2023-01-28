package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.UserRoleRepo;
import com.henry.facetcher.model.UserRole;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
public interface UserRoleDao extends BaseDao<UserRole, UserRoleRepo> {
    Boolean isUserRoleExistsByUserIdAndRoleId(Long userId, Long roleId);
}
