package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.UserLogDto;
import com.henry.facetcher.model.UserLog;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.UserLogMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 30/01/2023
 */
@Component
@AllArgsConstructor
public class UserLogTransformer implements BaseTransformer<UserLog, UserLogDto, UserLogMapper> {
    private final UserLogMapper userLogMapper;

    @Override
    public UserLogMapper getMapper() {
        return userLogMapper;
    }
}
