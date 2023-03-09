package com.henry.facetcher.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Henry Azer
 * @since 07/03/2023
 */
public interface StorageService {
    String uploadFile(MultipartFile file, String fileName);
    void removeFile(String bucketName, String fileObjKeyName);
}
