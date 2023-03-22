package com.henry.facetcher.service;

import com.henry.facetcher.dao.CloudFileDao;
import com.henry.facetcher.dto.CloudFileDto;
import com.henry.facetcher.model.CloudFile;
import com.henry.facetcher.service.base.BaseService;
import com.henry.facetcher.transformer.CloudFileTransformer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
public interface CloudFileService extends BaseService<CloudFile, CloudFileDto, CloudFileDao, CloudFileTransformer> {
    List<CloudFileDto> findCloudAssetsFiles(String type);
    CloudFileDto uploadCloudFile(MultipartFile file, String type, String fileName);
}
