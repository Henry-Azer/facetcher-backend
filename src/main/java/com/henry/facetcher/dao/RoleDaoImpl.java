package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.RoleRepo;
import com.henry.facetcher.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
@Component
public class RoleDaoImpl implements RoleDao {
    private final RoleRepo roleRepo;

    public RoleDaoImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleRepo getRepository() {
        return roleRepo;
    }

    @Override
    public List<Role> findAllByMarkedAsDeletedFalse() {
        return getRepository().findAllByMarkedAsDeletedFalse();
    }
}
