package com.example.s3ObjectOperations.dto;

import lombok.Getter;

import java.util.List;

/**
 * @author Vivek Kumar Sinha
 */
@Getter
public class SuccessResponseDTO<T> extends BaseResponseDTO<T> {

    public SuccessResponseDTO(T data) {
        super();
        this.data = data;
    }

    public SuccessResponseDTO(T data, List<String> messages) {
        super();
        this.data = data;
        this.messages = messages;
    }
}
