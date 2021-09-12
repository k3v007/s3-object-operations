package com.example.s3ObjectOperations.constant;

import com.amazonaws.HttpMethod;

/**
 * @author Vivek Kumar Sinha
 */
public interface AppConstant {

    Integer DEFAULT_PRESIGNED_URL_EXPIRY_IN_MINUTES = 60;
    HttpMethod DEFAULT_PRESIGNED_URL_HTTP_METHOD = HttpMethod.GET;
}
