package com.example.s3ObjectOperations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vivek Kumar Sinha
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponseDTO {

    private String fileUrl;
}
