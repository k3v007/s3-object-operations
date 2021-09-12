package com.example.s3ObjectOperations.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author Vivek Kumar Sinha
 */
@Getter
public class FailureResponseDTO<T> extends BaseResponseDTO<T> {

    public FailureResponseDTO(T data) {
        super();
        this.data = data;
    }

    public FailureResponseDTO(List<String> errors) {
        super();
        this.error = errors;
    }
}
