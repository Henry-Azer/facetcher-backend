package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserLogDao;
import com.henry.facetcher.dto.UserLogDto;
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
    private final UserLogDao userLogDao;

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
}
