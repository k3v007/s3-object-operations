package com.example.s3ObjectOperations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Vivek Kumar Sinha
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUrlResponseDTO {

    private String fileUrl;
    private Date fileUrlExpiryTime;
}
