package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.dto.ArticleDto;
import com.wangchenglong.myblog.model.dto.ArticleUpdateDto;
import com.wangchenglong.myblog.model.vo.ArticleVo;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.ArticleService;
import com.wangchenglong.myblog.utils.JWTUtils;
import com.wangchenglong.myblog.utils.QiniuUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

    @ApiOperation("删除文章")
    @ApiImplicitParam(name = "articleId", value = "文章id")
    @GetMapping("/delete/{articleId}")
    public Result<Object> deleteArticleById(@PathVariable Long articleId, HttpServletRequest httpServletRequest) {
        if (articleId == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        //Result result = articleService.deleteArticleById(articleId, userId);
        return articleService.deleteArticleById(articleId, userId);
    }

    @ApiOperation("批量删除文章")
    @ApiImplicitParam(name = "ids", value = "文章编号批量编号")
    @PostMapping("/deletebatch")
    public Result<Object> deleteArticlesBatchByIds(@RequestParam("ids") List<Long> ids, HttpServletRequest httpServletRequest) {
        if (ids == null || ids.isEmpty()) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.deleteArticlesBatch(ids, userId);
    }

    @ApiOperation("根据ID获取文章")
    @GetMapping("/find/{articleId}")
    public Result<ArticleVo> findArticleById(@PathVariable("articleId") Long articleId, HttpServletRequest httpServletRequest) {
        if (articleId == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.findArticleById(articleId, userId);
    }

    @ApiOperation("文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页面显示个数")
    })
    @PostMapping("/list")
    public Result<PageVo<ArticleVo>> listArticles(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer count, HttpServletRequest httpServletRequest) {
        if (page == null || count == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.listArticles(page, count, userId);
    }

    @ApiOperation("新增文章")
    @PostMapping("/save")
    public Result<Object> saveArticle(@RequestBody ArticleDto articleDto, HttpServletRequest httpServletRequest) {
        if (articleDto == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.saveArticle(articleDto, userId);
    }

    @ApiOperation("文章是否置顶")
    @GetMapping("/istop/{articleId}")
    public Result<Object> updateArticleWeight(@PathVariable("articleId") Long articleId, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(articleId)) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.updateArticleWeight(articleId, userId);
    }

    @ApiOperation("文章修改")
    @PostMapping("/update")
    public Result<Object> updateArticleById(@RequestBody ArticleUpdateDto articleUpdateDto, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(articleUpdateDto)) {
            return Result.fail(ErrorCode.UPLOAD_IS_FAILL.getCode(), ErrorCode.UPLOAD_IS_FAILL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.updateArticle(articleUpdateDto, userId);
    }

    @ApiOperation("文章查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "搜索关键字"),
            @ApiImplicitParam(name = "page", value = "页码"),
            @ApiImplicitParam(name = "count", value = "页面显示个数")
    })
    @PostMapping("/search")
    public Result<PageVo<ArticleVo>> searchArticle(@RequestParam("keyword") String keyWord, @RequestParam("page") Integer page, @RequestParam("count") Integer count, HttpServletRequest httpServletRequest) {
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