package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserMessageDao;
import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.model.UserMessage;
import com.henry.facetcher.transformer.UserMessageTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserMessageServiceImpl implements UserMessageService {
    private final UserMessageTransformer userMessageTransformer;
    private final UserMessageDao userMessageDao;
    private final UserService userService;

    @Override
    public UserMessageTransformer getTransformer() {
        return userMessageTransformer;
    }

    @Override
    public UserMessageDao getDao() {
        return userMessageDao;
    }

    @Override
    public List<UserMessageDto> findAllUserMessages() {
        log.info("UserMessageService: findById() called");
        return getTransformer().transformEntityToDto(getDao().findAllUserMessages());
    }

    @Override
    public UserMessageDto create(UserMessageDto dto) {
        log.info("UserMessageService: create() called");
        UserMessage transformedDtoToEntity = getTransformer().transformDtoToEntity(dto);
        transformedDtoToEntity.setUser(userService.getDao().findById(userService.getCurrentUser().getId()).get());
        return getTransformer().transformEntityToDto(getDao().create(transformedDtoToEntity));
    }
}
