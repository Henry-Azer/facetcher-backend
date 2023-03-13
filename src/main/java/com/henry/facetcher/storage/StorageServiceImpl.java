package com.henry.facetcher.storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since  07/03/2023
 */
@Service
public class StorageServiceImpl implements StorageService {
    private final AmazonS3 amazonS3;
    private final String bucketName;
    private final String cdnUrl;
    private final String s3Url;

    public StorageServiceImpl(AmazonS3 amazonS3,
                              @Value("${cloud.aws.s3.url:}") String s3Url,
                              @Value("${cloud.aws.cdn.url:}") String cdnUrl,
                              @Value("${cloud.aws.bucket-name}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
        this.cdnUrl = cdnUrl;
        this.s3Url = s3Url;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String fileObjKeyName = String.format("%s", fileName);
        doUpload(bucketName, fileObjKeyName, Optional.of(metadata), file);
        String filePath;
        if (cdnUrl != null) filePath = "https://" + cdnUrl + "/" + fileName;
        else filePath = "https://" + s3Url + "/" + bucketName + "/" + fileName;
        return filePath;
    }

    private void doUpload(String bucketName, String fileObjKeyName, Optional<Map<String, String>> optionalMetaData, MultipartFile file) {
        if (file.isEmpty()) throw new IllegalStateException("StorageService - Cannot upload empty file");
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            optionalMetaData.ifPresent(map -> {
                if (!map.isEmpty()) map.forEach(objectMetadata::addUserMetadata);
            });
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, file.getInputStream(), objectMetadata);
            request.setCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(request);
        } catch (IOException exception) {
            throw new IllegalArgumentException("StorageService - Failed to get the file content: ", exception);
        } catch (AmazonServiceException exception) {
            throw new IllegalStateException("StorageService - Failed to upload the file: ", exception);
        }
    }

    @Override
    public void removeFile(String bucketName, String fileObjKeyName) {
        try {
            amazonS3.deleteObject(bucketName, fileObjKeyName);
        } catch (AmazonServiceException exception) {
            throw new IllegalStateException("StorageService - Failed to remove the file: ", exception);
        }
    }

}
