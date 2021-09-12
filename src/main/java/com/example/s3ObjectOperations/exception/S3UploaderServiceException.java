package com.example.s3ObjectOperations.exception;

/**
 * @author Vivek Kumar Sinha
 */
public class S3UploaderServiceException extends Exception {

    public S3UploaderServiceException(String message) {
        super(message);
    }

    public S3UploaderServiceException(String message, Exception exception) {
        super(message, exception);
    }

    public S3UploaderServiceException(Exception exception) {
        super(exception);
    }
}
