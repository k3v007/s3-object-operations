package com.example.s3ObjectOperations.service;

import com.example.s3ObjectOperations.dto.FileUrlResponseDTO;
import com.example.s3ObjectOperations.dto.UploadFileRequestDTO;
import com.example.s3ObjectOperations.dto.UploadFileResponseDTO;

import java.io.IOException;

/**
 * @author Vivek Kumar Sinha
 */
public interface IFileService {

    UploadFileResponseDTO uploadFile(UploadFileRequestDTO uploadFileRequest) throws IOException;

    FileUrlResponseDTO getFileUrl(String bucketName, String key, Boolean presignedUrlRequired, Integer expiryInMinutes);
}
