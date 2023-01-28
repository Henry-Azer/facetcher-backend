package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserDao;
import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.model.User;
import com.henry.facetcher.enums.UserGender;
import com.henry.facetcher.enums.UserMartialStatus;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.transformer.UserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserTransformer userTransformer;
    private final UserDao userDao;
    private final JWTAuthenticationManager jwtAuthenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserTransformer userTransformer, UserDao userDao, JWTAuthenticationManager jwtAuthenticationManager, @Lazy PasswordEncoder passwordEncoder) {
        this.userTransformer = userTransformer;
        this.userDao = userDao;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }

    @Override
    public UserTransformer getTransformer() {
        return userTransformer;
    }

    @Override
    public UserDto create(UserDto dto) {
        log.info("UserService: create() called");
        // check if email already exists
        if (getDao().isUserExistsByEmail(dto.getEmail())) throw new EntityExistsException("User email already exists - " + dto.getEmail());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }

    @Override
    public UserDto findById(Long userId) {
        log.info("UserService: findById() called");
        Optional<User> optionalUser = getDao().findById(userId);
        if (optionalUser.isEmpty()) throw new EntityExistsException("User not exists for id: " + userId);
        return getTransformer().transformEntityToDto(optionalUser.get());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        log.info("UserService: findUserByEmail() called");
        Optional<User> optionalUser = getDao().findUserByEmail(email);
        if (optionalUser.isEmpty()) throw new EntityExistsException("User not exists for email: " + email);
        return getTransformer().transformEntityToDto(optionalUser.get());
    }

    @Override
    public List<UserGender> getUserGenders() {
        log.info("UserService: getUserGenders() called");
        return new ArrayList<>(EnumSet.allOf(UserGender.class));
    }

    @Override
    public List<UserMartialStatus> getUserMartialStatuses() {
        log.info("UserService: getUserMartialStatuses() called");
        return new ArrayList<>(EnumSet.allOf(UserMartialStatus.class));
    }

    @Override
    public UserDto getCurrentUser() {
        log.info("UserService: getCurrentUser() called");
        return findUserByEmail(jwtAuthenticationManager.getCurrentUserEmail());
    }

    @Override
    public Boolean isUserExistsByEmail(String email) {
        log.info("UserService: isUserExistsByEmail() called");
        return getDao().isUserExistsByEmail(email);
    }

    @Override
    public UserDto toggleUserDeletionById(Long userId) {
        log.info("UserService: toggleUserDeletionById() called");
        UserDto userDto = findById(userId);
        userDto.setMarkedAsDeleted(!userDto.getMarkedAsDeleted());
        return update(userDto, userDto.getId());
    }
}
