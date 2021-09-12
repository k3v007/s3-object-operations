package com.example.s3ObjectOperations.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Vivek Kumar Sinha
 */
@Getter
@NoArgsConstructor
public class BaseResponseDTO<T> {

    protected boolean success;
    protected T data = (T) Collections.emptyMap();
    protected List<String> messages = new ArrayList<>();
    protected List<String> error = new ArrayList<>();
}
