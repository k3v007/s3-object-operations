package com.example.s3ObjectOperations.service.platform;

import java.net.URL;

/**
 * @author Vivek Kumar Sinha
 */
public interface IS3UploaderService {

    URL uploadFile(byte[] file);
}
