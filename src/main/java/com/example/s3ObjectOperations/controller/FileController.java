package com.example.s3ObjectOperations.controller;

import com.example.s3ObjectOperations.dto.FileUrlResponseDTO;
import com.example.s3ObjectOperations.dto.SuccessResponseDTO;
import com.example.s3ObjectOperations.dto.UploadFileRequestDTO;
import com.example.s3ObjectOperations.dto.UploadFileResponseDTO;
import com.example.s3ObjectOperations.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Vivek Kumar Sinha
 */
@RestController
@RequestMapping("/api")
public class FileController {

    private final IFileService fileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/uploadFile")
    SuccessResponseDTO<UploadFileResponseDTO> uploadFile(@RequestBody UploadFileRequestDTO uploadFileRequest) throws IOException {
        UploadFileResponseDTO response = fileService.uploadFile(uploadFileRequest);
        return new SuccessResponseDTO<>(response);
    }

    @GetMapping(value = "/getFileUrl")
    SuccessResponseDTO<FileUrlResponseDTO> getFileUrl(
            @RequestParam("bucketName") String bucketName,
            @RequestParam("key") String key,
            @RequestParam(value = "presignedUrlRequired", required = false) Boolean presignedUrlRequired,
            @RequestParam(value = "expiryInMinutes", required = false) Integer expiryInMinutes) {
        FileUrlResponseDTO response = fileService.getFileUrl(bucketName, key, presignedUrlRequired, expiryInMinutes);
        return new SuccessResponseDTO<>(response);
    }
}
