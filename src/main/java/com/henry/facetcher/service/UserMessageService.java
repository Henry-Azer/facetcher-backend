package com.henry.facetcher.service;

import com.henry.facetcher.dao.UserMessageDao;
import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.model.UserMessage;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.UserMessageTransformer;

import java.util.List;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
public interface UserMessageService extends BaseService<UserMessage, UserMessageDto, UserMessageDao, UserMessageTransformer> {
    List<UserMessageDto> findAllUserMessages();
}
