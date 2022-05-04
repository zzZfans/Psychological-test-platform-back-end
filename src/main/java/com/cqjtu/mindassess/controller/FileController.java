package com.cqjtu.mindassess.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.service.IFileService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhangning
 *      上传文件接口(向外暴露)
 */
@Api(tags = {"文件上传控制器"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    IFileService fileService;

    @ApiOperationSupport(author = "zhangning")
    @ApiOperation("单个文件上传")
//    @SaCheckPermission(value = {"file-upload"},orRole = {"general"})
    @PostMapping("/upload")
    public ApiResponse<?> fileUpload(MultipartFile file){
        if(ObjectUtils.isEmpty(file)){
            return ApiResponse.fail(200,"文件不能为空");
        }
        String assessUrl = fileService.fileUpload(file);
        return ApiResponse.success(200,"上传成功",assessUrl);
    }
}
