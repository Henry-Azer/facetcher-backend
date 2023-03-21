package com.henry.facetcher.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.henry.facetcher.service.ConfigValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static com.henry.facetcher.constants.FacetcherConstants.CDN_URL;
import static com.henry.facetcher.constants.FacetcherConstants.S3_URL;

@Slf4j
@Component
public class StorageManager {
    private final AmazonS3 amazonS3;
    private final ConfigValueService configValueService;

    public StorageManager(AmazonS3 amazonS3, ConfigValueService configValueService) {
        this.amazonS3 = amazonS3;
        this.configValueService = configValueService;
    }

    public String uploadFile(MultipartFile file, String fileName, String cdn, String bucket) {
        log.info("StorageManager: uploadFile() - called");
        StorageService storageService = new StorageServiceImpl(
                amazonS3, configValueService.findConfigValueByConfigKey(S3_URL),
                configValueService.findConfigValueByConfigKey(CDN_URL).replace("xx", cdn), bucket);
        return storageService.uploadFile(file, fileName);
    }

    public void removeFile(String url, String bucket) {
        log.info("StorageManager: removeFile() - called");
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        StorageService storageService = new StorageServiceImpl(amazonS3, url, null, bucket);
        storageService.removeFile(bucket, fileName);
    }
}
