package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.ConfigValueRepo;
import com.henry.facetcher.model.ConfigValue;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
public interface ConfigValueDao extends BaseDao<ConfigValue, ConfigValueRepo> {
    Optional<ConfigValue> findConfigValueByConfigKey(String key);
}
