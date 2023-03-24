package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserDao;
import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.dto.UserPasswordDto;
import com.henry.facetcher.dto.base.notification.NotificationDto;
import com.henry.facetcher.mail.MailSender;
import com.henry.facetcher.model.User;
import com.henry.facetcher.enums.Gender;
import com.henry.facetcher.enums.UserMartialStatus;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.storage.StorageManager;
import com.henry.facetcher.transformer.UserTransformer;
import com.henry.facetcher.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.*;

import static com.henry.facetcher.constants.FacetcherConstants.*;

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
    private final StorageManager storageService;
    private final MailSender mailSender;
    private final ConfigValueService configValueService;

    public UserServiceImpl(UserTransformer userTransformer, UserDao userDao, JWTAuthenticationManager jwtAuthenticationManager, @Lazy PasswordEncoder passwordEncoder, StorageManager storageService, MailSender mailSender, ConfigValueService configValueService) {
        this.userTransformer = userTransformer;
        this.userDao = userDao;
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.storageService = storageService;
        this.mailSender = mailSender;
        this.configValueService = configValueService;
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
    @Transactional
    public UserDto create(UserDto dto) {
        log.info("UserService: create() called");
        if (getDao().isUserExistsByEmail(dto.getEmail())) throw new EntityExistsException("User email already exists - " + dto.getEmail());
        String password = dto.getPassword();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserDto userDto = getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
        userDto.setPassword(password);
        mailSender.sendEmail(constructCreateUserNotificationDto(userDto));
        return userDto;
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
    public List<Gender> getUserGenders() {
        log.info("UserService: getUserGenders() called");
        return new ArrayList<>(EnumSet.allOf(Gender.class));
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

    @Override
    public UserDto setUserProfilePicture(MultipartFile photo) {
        log.info("UserService: setUserProfilePicture() called");
        UserDto userDto = findUserByEmail(jwtAuthenticationManager.getCurrentUserEmail());
        if (userDto.getProfilePictureUrl() != null) storageService.removeFile(userDto.getProfilePictureUrl(), configValueService.findConfigValueByConfigKey(FPP_BUCKET));
        userDto.setProfilePictureUrl(storageService.uploadFile(photo, StringUtil.getRandomImageName(photo.getOriginalFilename(),
                userDto.getId().toString()), configValueService.findConfigValueByConfigKey(FPP_CDN), configValueService.findConfigValueByConfigKey(FPP_BUCKET)));
        return update(userDto, userDto.getId());
    }

    @Override
    public UserDto removeUserProfilePicture() {
        log.info("UserService: removeUserProfilePicture() called");
        UserDto userDto = findUserByEmail(jwtAuthenticationManager.getCurrentUserEmail());
        if (userDto.getProfilePictureUrl() != null) {
            storageService.removeFile(userDto.getProfilePictureUrl(), configValueService.findConfigValueByConfigKey(FPP_BUCKET));
            userDto.setProfilePictureUrl(null);
        }
        return update(userDto, userDto.getId());
    }

    @Override
    public UserDto updateUserPassword(UserPasswordDto userPasswordDto) {
        log.info("UserService: updateUserPassword() called");
        UserDto userDto = findUserByEmail(jwtAuthenticationManager.getCurrentUserEmail());
        if (!passwordEncoder.matches(userPasswordDto.getPassword(), userDto.getPassword()))
            throw new IllegalArgumentException("User old password is invalid.");
        userDto.setPassword(passwordEncoder.encode(userPasswordDto.getNewPassword()));
        return update(userDto, userDto.getId());
    }

    private NotificationDto constructCreateUserNotificationDto(UserDto userDto) {
        log.info("UserService: constructCreateUserNotificationDto() called");
        NotificationDto notificationDto = new NotificationDto();
        // change to user mail when be out of ses sandbox
        notificationDto.setToEmail(configValueService.findConfigValueByConfigKey(TO_MAIL));
        notificationDto.setTemplateName(configValueService.findConfigValueByConfigKey(CREATE_ACCOUNT_MAIL_TEMPLATE));
        notificationDto.setSubject(configValueService.findConfigValueByConfigKey(CREATE_ACCOUNT_MAIL_SUBJECT));
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put(configValueService.findConfigValueByConfigKey(CREATE_ACCOUNT_MAIL_MODEL), userDto);
        notificationDto.setTemplateModel(templateModel);
        return notificationDto;
    }
}
