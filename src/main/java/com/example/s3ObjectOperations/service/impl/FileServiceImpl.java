package com.example.s3ObjectOperations.service.impl;

import com.example.s3ObjectOperations.dto.FileUrlResponseDTO;
import com.example.s3ObjectOperations.dto.PresignedUrlDTO;
import com.example.s3ObjectOperations.dto.UploadFileRequestDTO;
import com.example.s3ObjectOperations.dto.UploadFileResponseDTO;
import com.example.s3ObjectOperations.exception.S3UploaderServiceException;
import com.example.s3ObjectOperations.service.IFileService;
import com.example.s3ObjectOperations.service.platform.IS3RetrieverService;
import com.example.s3ObjectOperations.service.platform.IS3UploaderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Vivek Kumar Sinha
 */
@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    private final IS3UploaderService s3UploaderService;
    private final IS3RetrieverService s3RetrieverService;

    @Autowired
    public FileServiceImpl(IS3UploaderService s3UploaderService, IS3RetrieverService s3RetrieverService) {
        this.s3UploaderService = s3UploaderService;
        this.s3RetrieverService = s3RetrieverService;
    }

    @Override
    public UploadFileResponseDTO uploadFile(UploadFileRequestDTO uploadFileRequest) throws IOException {
        FileInputStream fis = new FileInputStream(uploadFileRequest.getFilePath());
        String fileExtension = FilenameUtils.getExtension(uploadFileRequest.getFilePath());
        String bucketName = uploadFileRequest.getBucketName();
        String key = String.format("%s/test_file_%d.%s", uploadFileRequest.getFolder(),
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(), fileExtension);
        log.info("bucketName={} :: key={}", bucketName, key);
        byte[] byteFile = IOUtils.toByteArray(fis);
        try {
            URL url = s3UploaderService.uploadFile(uploadFileRequest.getBucketName(), key, byteFile);
            return UploadFileResponseDTO.builder()
                    .fileUrl(url.toExternalForm())
                    .build();
        } catch (S3UploaderServiceException e) {
            log.error("FILE_SERVICE_ERROR :: Error in uploading file to S3 : {}", e.getMessage(), e);
            return UploadFileResponseDTO.builder()
                    .fileUrl(null)
                    .build();
        }
    }

    @Override
    public FileUrlResponseDTO getFileUrl(String bucketName, String key, Boolean presignedUrlRequired, Integer expiryInMinutes) {
        if (presignedUrlRequired) {
            PresignedUrlDTO presignedUrlDTO = s3RetrieverService.getPresignedUrl(bucketName, key, expiryInMinutes);
            return FileUrlResponseDTO.builder()
                    .fileUrl(presignedUrlDTO.getUrl().toExternalForm())
                    .fileUrlExpiryTime(presignedUrlDTO.getExpiration())
                    .build();
        } else {
            URL fileUrl = s3RetrieverService.getFileUrl(bucketName, key);
            return FileUrlResponseDTO.builder()
                    .fileUrl(fileUrl.toExternalForm())
                    .fileUrlExpiryTime(null)
                    .build();
        }
    }
}
