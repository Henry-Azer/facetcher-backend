package com.henry.facetcher.dao;

import com.henry.facetcher.dao.repo.UserMessageRepo;
import com.henry.facetcher.dto.UserMessageDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Component
public class UserMessageDaoImpl implements UserMessageDao {
    private final UserMessageRepo userMessageRepo;

    public UserMessageDaoImpl(UserMessageRepo userMessageRepo) {
        this.userMessageRepo = userMessageRepo;
    }

    @Override
    public UserMessageRepo getRepository() {
        return userMessageRepo;
    }

    @Override
    public List<UserMessageDto> findAllUserMessages() {
        return getRepository().findAllByMarkedAsDeletedFalse();
    }
}
