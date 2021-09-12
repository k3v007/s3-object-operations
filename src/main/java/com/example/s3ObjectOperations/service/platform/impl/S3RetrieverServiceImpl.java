package com.example.s3ObjectOperations.service.platform.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.example.s3ObjectOperations.constant.AppConstant;
import com.example.s3ObjectOperations.dto.PresignedUrlDTO;
import com.example.s3ObjectOperations.service.platform.IS3RetrieverService;
import com.example.s3ObjectOperations.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Date;
import java.util.Optional;

/**
 * @author Vivek Kumar Sinha
 */
@Slf4j
@Service
public class S3RetrieverServiceImpl implements IS3RetrieverService {

    private final AmazonS3 amazonS3;

    @Autowired
    public S3RetrieverServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public URL getFileUrl(String bucketName, String key) {
        return amazonS3.getUrl(bucketName, key);
    }

    @Override
    public PresignedUrlDTO getPresignedUrl(String bucketName, String key, Integer expiryTimeInMinutes) {
        log.info("Current Time: {}", new Date());
        Date expiration = DateUtil.getDeltaAddedDate(new Date(), Optional.ofNullable(expiryTimeInMinutes)
                .orElse(AppConstant.DEFAULT_PRESIGNED_URL_EXPIRY_IN_MINUTES));
        log.info("Expiration Time: {}", expiration);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key)
                .withMethod(AppConstant.DEFAULT_PRESIGNED_URL_HTTP_METHOD)
                .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return PresignedUrlDTO.builder()
                .url(url)
                .expiration(expiration)
                .build();
    }
}
