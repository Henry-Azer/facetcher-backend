package com.henry.facetcher.service;

import com.henry.facetcher.dao.CloudFileDao;
import com.henry.facetcher.dto.CloudFileDto;
import com.henry.facetcher.model.CloudFile;
import com.henry.facetcher.storage.StorageManager;
import com.henry.facetcher.transformer.CloudFileTransformer;
import com.henry.facetcher.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

import static com.henry.facetcher.constants.FacetcherConstants.F_BUCKET;
import static com.henry.facetcher.constants.FacetcherConstants.F_CDN;

/**
 * @author Henry Azer
 * @since 13/03/2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class CloudFileServiceImpl implements CloudFileService {
    private final CloudFileTransformer cloudFileTransformer;
    private final CloudFileDao cloudFileDao;
    private final StorageManager storageManager;
    private final ConfigValueService configValueService;

    @Override
    public CloudFileTransformer getTransformer() {
        return cloudFileTransformer;
    }

    @Override
    public CloudFileDao getDao() {
        return cloudFileDao;
    }

    @Override
    public List<CloudFileDto> findCloudAssetsFiles(String type) {
        log.info("CloudFileService: findCloudAssetsFiles() - called");
        return getTransformer().transformEntityToDto(getDao().findCloudAssetsFiles(type));
    }

    @Override
    public CloudFileDto uploadCloudFile(MultipartFile file, String type, String fileName) {
        log.info("CloudFileService: uploadCloudFile() - called");
        CloudFileDto cloudFileDto = constructCloudFileDto(file, type, fileName);
        Optional<CloudFile> dbCloudFile = getDao().findCloudFileByFileName(cloudFileDto.getFileName());
        if (dbCloudFile.isPresent()) throw new EntityExistsException("File name already exists.");
        cloudFileDto.setFileURL(storageManager.uploadFile(file, cloudFileDto.getFileName(), cloudFileDto.getCdnCode(), cloudFileDto.getBucketName()));
        return getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(cloudFileDto)));
    }

    private CloudFileDto constructCloudFileDto(MultipartFile file, String type, String fileName) {
        log.info("CloudFileService: uploadCloudFile() - called");
        CloudFileDto cloudFileDto = new CloudFileDto();
        cloudFileDto.setBucketName(configValueService.findConfigValueByConfigKey(F_BUCKET));
        cloudFileDto.setCdnCode(configValueService.findConfigValueByConfigKey(F_CDN));
        cloudFileDto.setFileName(fileName + StringUtil.getFileExtension(file.getOriginalFilename()));
        cloudFileDto.setType(type);
        return cloudFileDto;
    }
}
