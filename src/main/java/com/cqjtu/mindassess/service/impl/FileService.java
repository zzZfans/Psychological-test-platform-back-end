package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.config.minioconf.MinioProperties;
import com.cqjtu.mindassess.service.IFileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    @Autowired
    MinioClient mc;
    @Autowired
    MinioProperties minioProperties;

    @Override
    public String fileUpload(MultipartFile mpFile) {
        String bucket = minioProperties.getBucket();
        String object = UUID.randomUUID().toString() + "/" + mpFile.getOriginalFilename();
        try {
            mc.putObject(PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(object)
                            .stream(mpFile.getInputStream(), mpFile.getSize(),-1)
                            .contentType(mpFile.getContentType())
                    .build());
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
        return minioProperties.getAccessUrlPrefix() + "/" + bucket + "/" + object;
    }
}
