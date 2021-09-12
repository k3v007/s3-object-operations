package com.example.s3ObjectOperations.service.platform;

import com.example.s3ObjectOperations.exception.S3UploaderServiceException;

import java.net.URL;

/**
 * @author Vivek Kumar Sinha
 */
public interface IS3UploaderService {

    URL uploadFile(String bucketName, String key, byte[] file) throws S3UploaderServiceException;
}
