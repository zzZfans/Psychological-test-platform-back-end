package com.cqjtu.mindassess.controller;

import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangning
 *      上传文件接口(向外暴露)
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    IFileService fileService;

    @PostMapping("/upload")
    public ApiResponse<?> fileUpload(MultipartFile file){
        String assessUrl = fileService.fileUpload(file);
        return ApiResponse.success(200,"上传成功",assessUrl);
    }
}
