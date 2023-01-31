package com.henry.facetcher.transformer;

import com.henry.facetcher.dto.ImageDto;
import com.henry.facetcher.model.Image;
import com.henry.facetcher.transformer.base.BaseTransformer;
import com.henry.facetcher.transformer.mapper.ImageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Henry Azer
 * @since 31/01/2023
 */
@Component
@AllArgsConstructor
public class ImageTransformer implements BaseTransformer<Image, ImageDto, ImageMapper> {
    private final ImageMapper imageMapper;

    @Override
    public ImageMapper getMapper() {
        return imageMapper;
    }
}
