package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.CloudFileDto;
import com.henry.facetcher.model.CloudFile;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.CloudFileMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Component
@AllArgsConstructor
public class CloudFileTransformer implements BaseTransformer<CloudFile, CloudFileDto, CloudFileMapper> {
    private final CloudFileMapper cloudFileMapper;

    @Override
    public CloudFileMapper getMapper() {
        return cloudFileMapper;
    }
}
