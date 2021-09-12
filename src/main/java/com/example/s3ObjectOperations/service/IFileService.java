package com.example.s3ObjectOperations.service;

import com.example.s3ObjectOperations.dto.FileUrlResponseDTO;
import com.example.s3ObjectOperations.dto.UploadFileResponseDTO;

/**
 * @author Vivek Kumar Sinha
 */
public interface IFileService {

    UploadFileResponseDTO uploadFile();

    FileUrlResponseDTO getFileUrl();
}
