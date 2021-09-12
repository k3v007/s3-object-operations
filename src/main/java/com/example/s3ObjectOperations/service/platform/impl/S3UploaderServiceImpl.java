package com.example.s3ObjectOperations.service.platform.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.example.s3ObjectOperations.service.platform.IS3UploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Vivek Kumar Sinha
 */
@Service
public class S3UploaderServiceImpl implements IS3UploaderService {

    private final AmazonS3 amazonS3;

    @Autowired
    public S3UploaderServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public URL uploadFile(String bucketName, String key, byte[] file) {
        TransferManager transferManager = TransferManagerBuilder.standard()
                .withS3Client(amazonS3)
                .withMultipartUploadThreshold((long) 5 * 1024 * 1025)
                .build();
        InputStream inputStream = new ByteArrayInputStream(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, null)
                .withCannedAcl(CannedAccessControlList.Private);
        Upload upload = transferManager.upload(putObjectRequest);

        System.out.println("Object upload started");
        try {
            upload.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Object upload complete");

        return amazonS3.getUrl(bucketName, key);
    }
}
