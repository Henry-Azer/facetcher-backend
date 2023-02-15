package com.henry.facetcher.dao;

import com.henry.facetcher.dao.base.BaseDao;
import com.henry.facetcher.dao.repo.UserMessageRepo;
import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.model.UserMessage;

import java.util.List;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
public interface UserMessageDao extends BaseDao<UserMessage, UserMessageRepo> {
    List<UserMessageDto> findAllUserMessages();
}
