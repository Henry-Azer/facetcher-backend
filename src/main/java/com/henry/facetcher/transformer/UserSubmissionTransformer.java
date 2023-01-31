package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.UserSubmissionDto;
import com.henry.facetcher.model.UserSubmission;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.UserSubmissionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Component
@AllArgsConstructor
public class UserSubmissionTransformer implements BaseTransformer<UserSubmission, UserSubmissionDto, UserSubmissionMapper> {
    private final UserSubmissionMapper userSubmissionMapper;

    @Override
    public UserSubmissionMapper getMapper() {
        return userSubmissionMapper;
    }
}
