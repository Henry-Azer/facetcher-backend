package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.UserRepository;
import com.henry.facetcher.model.User;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public interface UserDao extends BaseDao<User, UserRepository> {
    Optional<User> findUserByEmail(String email);
    Boolean isUserExistsByEmail(String email);
}
