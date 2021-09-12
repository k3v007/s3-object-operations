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
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vivek Kumar Sinha
 */
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping(value = "/uploadFile")
    SuccessResponseDTO<UploadFileResponseDTO> uploadFile(@RequestBody UploadFileRequestDTO uploadFileRequest) {
        UploadFileResponseDTO response = fileService.uploadFile();
        return new SuccessResponseDTO<>(response);
    }

    @GetMapping(value = "/getFileUrl")
    SuccessResponseDTO<FileUrlResponseDTO> getFileUrl(@RequestBody UploadFileRequestDTO uploadFileRequest) {
        FileUrlResponseDTO response = fileService.getFileUrl();
        return new SuccessResponseDTO<>(response);
    }
}
