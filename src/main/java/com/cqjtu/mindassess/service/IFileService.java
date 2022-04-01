package com.cqjtu.mindassess.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author zhangning
 *
 *  上传文件到Minio
 */
public interface IFileService {

    String fileUpload(MultipartFile mpFile);
}
