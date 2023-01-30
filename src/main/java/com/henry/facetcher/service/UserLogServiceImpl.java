package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserLogDao;
import com.henry.facetcher.dto.UserLogDto;
import com.henry.facetcher.dto.UserLoggingDto;
import com.henry.facetcher.enums.UserLogStatus;
import com.henry.facetcher.manager.JWTAuthenticationManager;
import com.henry.facetcher.model.UserLog;
import com.henry.facetcher.transformer.UserLogTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserLogServiceImpl implements UserLogService {
    private final UserLogTransformer userLogTransformer;
    private final UserService userService;
    private final UserLogDao userLogDao;
    private final JWTAuthenticationManager authenticationManager;

    @Override
    public UserLogTransformer getTransformer() {
        return userLogTransformer;
    }

    @Override
    public UserLogDao getDao() {
        return userLogDao;
    }

    @Override
    public UserLogDto create(UserLogDto dto) {
        log.info("UserLogService: create() called");
        Optional<UserLog> lastCurrentUserLog = getDao().findLastCurrentUserLogByUserIdAndStatus(dto.getUser().getId(), dto.getLogStatus());
        if (lastCurrentUserLog.isPresent()) dto.setLogCount(lastCurrentUserLog.get().getLogCount() + 1);
        else dto.setLogCount(1L);
        UserLog transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }

    @Override
    public UserLoggingDto findLastCurrentUserLog() {
        log.info("UserLogService: findLastCurrentUserLog() called");
        // TODO : refactor to admin portal specifications
        Optional<UserLog> lastCurrentUserLogin = getDao().findLastCurrentUserLogByUserIdAndStatus(userService.findUserByEmail(authenticationManager.getCurrentUserEmail()).getId(), UserLogStatus.LOGIN);
        Optional<UserLog> lastCurrentUserLogout = getDao().findLastCurrentUserLogByUserIdAndStatus(userService.findUserByEmail(authenticationManager.getCurrentUserEmail()).getId(), UserLogStatus.LOGOUT);
        return UserLoggingDto.builder().loginDto(getTransformer().transformEntityToDto(lastCurrentUserLogin.get()))
                .logoutDto(getTransformer().transformEntityToDto(lastCurrentUserLogout.get())).build();
    }
}
