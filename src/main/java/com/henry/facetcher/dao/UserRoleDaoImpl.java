package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserRoleRepo;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Component
public class UserRoleDaoImpl implements UserRoleDao {
    private final UserRoleRepo userRoleRepo;

    public UserRoleDaoImpl(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public UserRoleRepo getRepository() {
        return userRoleRepo;
    }

    @Override
    public Boolean isUserRoleExistsByUserIdAndRoleId(Long userId, Long roleId) {
        return getRepository().existsByUserIdAndRoleId(userId, roleId);
    }
}
