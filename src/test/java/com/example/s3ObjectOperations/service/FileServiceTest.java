package com.example.s3ObjectOperations.service;

import com.example.s3ObjectOperations.dto.FileUrlResponseDTO;
import com.example.s3ObjectOperations.dto.UploadFileRequestDTO;
import com.example.s3ObjectOperations.dto.UploadFileResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vivek Kumar Sinha
 */
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileServiceTest {

    @Autowired
    private IFileService fileService;

    @Test
    public void checkAllInjectedBeans() {
        assertThat(fileService).isNotNull();
    }

    @Test
    @Order(1)
    public void givenFilePathAndBucketNameAndKey_shouldUploadFileToS3() throws IOException {
        UploadFileRequestDTO uploadFileRequest = UploadFileRequestDTO.builder()
                .filePath("/Users/viveksinha/Desktop/test-file.zip")
                .bucketName("my-bucket")
                .folder("test-folder")
                .build();
        UploadFileResponseDTO response = fileService.uploadFile(uploadFileRequest);
        assertThat(response).isNotNull();
        assertThat(response.getFileUrl()).isNotNull();
        log.info("fileUrl={}", response.getFileUrl());
    }

    @Test
    @Order(2)
    public void givenFilePathAndBucketNameAndKey_shouldReturnNullFileUrl() throws IOException {
        UploadFileRequestDTO uploadFileRequest = UploadFileRequestDTO.builder()
                .filePath("/Users/viveksinha/Desktop/test-file.zip")
                .bucketName("my-bucketV2")
                .folder("test-folder")
                .build();
        // s3 bucket doesn't exist
        UploadFileResponseDTO response = fileService.uploadFile(uploadFileRequest);
        assertThat(response).isNotNull();
        assertThat(response.getFileUrl()).isNull();
        log.info("fileUrl={}", response.getFileUrl());
    }

    @Test
    @Order(3)
    public void givenBucketNameAndKey_shouldReturnFileUrl() {
        String bucketName = "my-bucket";
        String key = "1586159597034_test.csv";
        FileUrlResponseDTO response = fileService.getFileUrl(bucketName, key, false, null);
        assertThat(response).isNotNull();
        assertThat(response.getFileUrl()).isNotNull();
        assertThat(response.getFileUrlExpiryTime()).isNull();
        log.info("fileUrl={} :: fileUrlExpiryTime={},", response.getFileUrl(), response.getFileUrlExpiryTime());
    }

    @Test
    @Order(4)
    public void givenBucketNameAndKeyAndExpiryTimeInMinutes_shouldReturnPresignedUrlAndExpiryTime() {
        String bucketName = "my-bucket";
        String key = "1586159597034_test.csv";
        Integer expiryTimeInMinutes = 60;
        FileUrlResponseDTO response = fileService.getFileUrl(bucketName, key, true, expiryTimeInMinutes);
        assertThat(response).isNotNull();
        assertThat(response.getFileUrl()).isNotNull();
        assertThat(response.getFileUrlExpiryTime()).isNotNull();
        log.info("fileUrl={} :: fileUrlExpiryTime={},", response.getFileUrl(), response.getFileUrlExpiryTime());
    }
}
