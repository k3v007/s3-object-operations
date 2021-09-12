package com.example.s3ObjectOperations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.net.URL;
import java.util.Date;

/**
 * @author Vivek Kumar Sinha
 */
@Data
@Builder
@AllArgsConstructor
public class PresignedUrlDTO {

    private URL url;
    private Date expiration;
}
