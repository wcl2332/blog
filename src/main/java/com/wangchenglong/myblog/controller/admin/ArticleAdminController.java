package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.dto.ArticleDto;
import com.wangchenglong.myblog.model.dto.ArticleUpdateDto;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.ArticleService;
import com.wangchenglong.myblog.utils.JWTUtils;
import com.wangchenglong.myblog.utils.QiniuUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/31 15:43
 * @Description: 管理端博客文章管理
 */
@Api(tags = "管理端-文章管理")
@RestController
@RequestMapping("/admin/api/article/")
public class ArticleAdminController {
    @Resource
    ArticleService articleService;
    @Resource
    QiniuUtils qiniuUtils;


    @Value("${file.upload-dir}")
    private String uploadDir;

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "articleId", value = "文章编号", dataType = "Long", required = true)
    @GetMapping("/delete/{id}")
    public Result deleteArticleById(@PathVariable Long articleId, HttpServletRequest httpServletRequest) {
        if (articleId == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        Result result = articleService.deleteArticleById(articleId, userId);
        return result;
    }

    @ApiOperation("批量删除文章")
    @PostMapping("/deletebatch")
    public Result deleteArticlesBatchByIds(@RequestParam("ids") List<Long> ids, HttpServletRequest httpServletRequest) {
        if (ids == null || ids.isEmpty()) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.deleteArticlesBatch(ids, userId);
    }

    @ApiOperation("根据ID获取文章")
    @GetMapping("/find/{articleId}")
    public Result findArticleById(@PathVariable("articleId") Long articleId, HttpServletRequest httpServletRequest) {
        if (articleId == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.findArticleById(articleId, userId);
    }

    @ApiOperation("文章列表")
    @PostMapping("/list")
    public Result listArticles(@RequestParam("page") Integer page, @RequestParam("count") Integer count, HttpServletRequest httpServletRequest) {
        if (page == null || count == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.listArticles(page, count, userId);
    }

    @ApiOperation("新增文章")
    @PostMapping("/save")
    public Result saveArticle(@RequestBody ArticleDto articleDto, HttpServletRequest httpServletRequest) {
        if (articleDto == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.saveArticle(articleDto, userId);
    }

    @ApiOperation("上传图片")
    @PostMapping("/upload")
    public Result uploadImage(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
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

    @ApiOperation("文章置顶")
    @GetMapping("/istop/{articleId}")
    public Result updateArticleWeight(@PathVariable("articleId") Long articleId, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(articleId)) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.updateArticleWeight(articleId, userId);
    }

    @ApiOperation("文章修改")
    @PostMapping("/update")
    public Result updateArticleById(@RequestBody ArticleUpdateDto articleUpdateDto, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(articleUpdateDto)) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.updateArticle(articleUpdateDto, userId);
    }

    @ApiOperation("文章查询")
    @PostMapping("/search")
    public Result searchArticle(@RequestParam("keyword") String keyWord, @RequestParam("page") Integer page, @RequestParam("count") Integer count, HttpServletRequest httpServletRequest) {
        if (StringUtils.isBlank(keyWord)) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        if (Objects.isNull(page) || Objects.isNull(count)) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.searchArticle(keyWord, page, count, userId);
    }
}