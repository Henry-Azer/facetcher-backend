package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.RoleRepo;
import com.henry.facetcher.model.Role;

import java.util.List;

/**
 * @author Henry Azer
 * @since 28/01/2023
 */
public interface RoleDao extends BaseDao<Role, RoleRepo> {
    List<Role> findAllByMarkedAsDeletedFalse();
}
