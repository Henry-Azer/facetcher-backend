package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.UserDto;
import com.henry.facetcher.model.User;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
@Component
@AllArgsConstructor
public class UserTransformer implements BaseTransformer<User, UserDto, UserMapper> {
    private final UserMapper userMapper;

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }
}
