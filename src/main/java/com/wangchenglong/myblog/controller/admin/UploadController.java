package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/8 11:05
 * @Description:
 */
@Api(tags = "文件上传")
@RequestMapping("/admin/api")
@RestController
public class UploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @ApiOperation("上传图片")
    @ApiImplicitParam(name = "image", value = "上传图片", dataType = "MultipartFile", required = true)
    @PostMapping("/upload")
    public Result<String> uploadImage(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
        if (file == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        //原始文件名称 比如 aa.png
        String originalFilename = file.getOriginalFilename();
        //唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        Path targetLocation = Paths.get(uploadDir).resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation);
        } catch (IOException e) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        // 返回文件访问 URL
        String fileDownloadUri = request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort() +
                "/image/" + fileName;
        return Result.success(fileDownloadUri);
    }
}