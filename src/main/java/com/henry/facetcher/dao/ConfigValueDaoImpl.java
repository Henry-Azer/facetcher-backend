package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.ConfigValueRepo;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Component
public class ConfigValueDaoImpl implements ConfigValueDao {
    private final ConfigValueRepo configValueRepo;

    public ConfigValueDaoImpl(ConfigValueRepo configValueRepo) {
        this.configValueRepo = configValueRepo;
    }

    @Override
    public ConfigValueRepo getRepository() {
        return configValueRepo;
    }
}
