package com.example.s3ObjectOperations.service.platform;

import com.example.s3ObjectOperations.dto.PresignedUrlDTO;

import java.net.URL;

/**
 * @author Vivek Kumar Sinha
 */
public interface IS3RetrieverService {

    URL getFileUrl(String bucketName, String key);

    PresignedUrlDTO getPresignedUrl(String bucketName, String key, Integer expiryTimeInMinutes);
}
