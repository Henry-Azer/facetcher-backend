package com.henry.facetcher.storage;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class StorageManager {
    private final AmazonS3 amazonS3;
    private final String cdnURL;
    private final String s3URL;

    public StorageManager(AmazonS3 amazonS3,
                          @Value("${cloud.aws.s3.url}") String s3URL,
                          @Value("${cloud.aws.cdn.url}") String cdnURL) {
        this.amazonS3 = amazonS3;
        this.cdnURL = cdnURL;
        this.s3URL = s3URL;
    }

    public String uploadFile(MultipartFile file, String fileName, String cdn, String bucket) {
        log.info("StorageManager: uploadFile() - called");
        StorageService storageService = new StorageServiceImpl(amazonS3, s3URL, cdnURL.replace("xx", cdn), bucket);
        return storageService.uploadFile(file, fileName);
    }

    public void removeFile(String url, String bucket) {
        log.info("StorageManager: removeFile() - called");
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        StorageService storageService = new StorageServiceImpl(amazonS3, url, null, bucket);
        storageService.removeFile(bucket, fileName);
    }
}
