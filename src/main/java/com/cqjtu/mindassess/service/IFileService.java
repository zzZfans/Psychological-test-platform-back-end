package com.cqjtu.mindassess.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author zhangning
 *
 *  上传文件到Minio
 */
public interface IFileService {

    /**
     * 上传文件到Minio
     * @param mpFile - MultipartFile
     * @return - 上传成功返回可访问的URL
     */
    String fileUpload(MultipartFile mpFile);
}
