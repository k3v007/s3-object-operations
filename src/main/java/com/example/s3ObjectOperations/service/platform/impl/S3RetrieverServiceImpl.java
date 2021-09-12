package com.example.s3ObjectOperations.service.platform.impl;

import com.example.s3ObjectOperations.service.platform.IS3RetrieverService;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * @author Vivek Kumar Sinha
 */
@Service
public class S3RetrieverServiceImpl implements IS3RetrieverService {

    @Override
    public URL getFileUrl(String key, String bucketName, String region) {
        return null;
    }

    @Override
    public URL getPresignedUrl(String key, String bucketName, String region, Integer expiryTimeInMinutes) {
        return null;
    }
}
