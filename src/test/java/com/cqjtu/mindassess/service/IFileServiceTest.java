package com.cqjtu.mindassess.service;

import com.cqjtu.mindassess.config.minioconf.MinioProperties;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IFileServiceTest {

    @Autowired
    IFileService fileService;
    @Autowired
    MinioClient mc;
    @Autowired
    MinioProperties minioProperties;

    @Test
    void testMinioClient() throws FileNotFoundException {
        File file = new File("C:\\Users\\zhn\\Desktop\\maven构建webapp.png");
        FileInputStream is = new FileInputStream(file);
        try {
            ObjectWriteResponse objectWriteResponse = mc.putObject(PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(file.getName())
                    .stream(is, -1, 10485760)
                    .contentType("image/png")
                    .build());
            System.out.println(objectWriteResponse);
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
    }

    @Test
    void fileUpload() {

    }
}