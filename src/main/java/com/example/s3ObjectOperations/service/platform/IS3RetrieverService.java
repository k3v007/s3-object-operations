package com.example.s3ObjectOperations.service.platform;

import java.net.URL;

/**
 * @author Vivek Kumar Sinha
 */
public interface IS3RetrieverService {

    URL getFileUrl(String key, String bucketName, String region);

    URL getPresignedUrl(String key, String bucketName, String region, Integer expiryTimeInMinutes);
}
