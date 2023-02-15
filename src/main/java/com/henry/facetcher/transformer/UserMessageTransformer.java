package com.henry.facetcher.transformer;

import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.dto.UserMessageDto;
import com.henry.facetcher.model.UserMessage;
import com.henry.facetcher.transformer.mapper.UserMessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 15/02/2023
 */
@Component
@AllArgsConstructor
public class UserMessageTransformer implements BaseTransformer<UserMessage, UserMessageDto, UserMessageMapper> {
    private final UserMessageMapper userMessageMapper;

    @Override
    public UserMessageMapper getMapper() {
        return userMessageMapper;
    }
}
