package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserDao;
import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.model.User;
import com.henry.facetcher.enums.Gender;
import com.henry.facetcher.enums.UserMartialStatus;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserTransformer;

import java.util.List;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public interface UserService extends BaseService<User, UserDto, UserDao, UserTransformer> {
    UserDto findUserByEmail(String email);
    List<Gender> getUserGenders();
    List<UserMartialStatus> getUserMartialStatuses();
    UserDto getCurrentUser();
    Boolean isUserExistsByEmail(String email);
    UserDto toggleUserDeletionById(Long userId);
}
