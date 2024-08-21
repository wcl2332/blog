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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
            @ApiImplicitParam(name = "pageSize", value = "页面显示个数"),
            @ApiImplicitParam(name = "statusCode", value = "文章状态，当需要获取 草稿状态的时,传入当前参数为0，获取这已发布状态的可不传")
    })
    @PostMapping("/list")
    public Result<PageVo<ArticleVo>> listArticles(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer count, @RequestParam(value = "statusCode", required = false) Integer statusCode, HttpServletRequest httpServletRequest) {
        if (page == null || count == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.listArticles(page, count, statusCode, userId);
    }

    @ApiOperation("新增文章")
    @PostMapping("/save")
    public Result<Object> saveArticle(@Valid @RequestBody ArticleDto articleDto, HttpServletRequest httpServletRequest) {
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
    public Result<Object> updateArticleById(@Valid @RequestBody ArticleUpdateDto articleUpdateDto, HttpServletRequest httpServletRequest) {
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
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页面显示个数")
    })
    @PostMapping("/search")
    public Result<PageVo<ArticleVo>> searchArticle(@RequestParam("keyword") String keyWord, @RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer count, HttpServletRequest httpServletRequest) {
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

    @ApiOperation("文章多条件查询")
    @PostMapping("/searchByCondition")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题（支持模糊查询）", required = false),
            @ApiImplicitParam(name = "categroyId", value = "分类标签", required = false),
            @ApiImplicitParam(name = "tagId", value = "标签id", required = false),
            @ApiImplicitParam(name = "isTop", value = "是否置顶（不置顶 0 ，置顶 大于等于1 ）", required = false),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = false),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面显示个数", required = true)
    })
    public Result<PageVo<ArticleVo>> searchArticleByCondition(
            @RequestParam("pageNum") Integer page,
            @RequestParam("pageSize") Integer count,
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "categroyId",required = false) Integer categroyId,
            @RequestParam(value = "tagId",required = false) Integer tagId,
            @RequestParam(value = "isTop",required = false) Integer isTop,
            @RequestParam(value = "startTime",required = false) String startTime,
            @RequestParam(value = "endTime",required = false) String endTime,
            HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return articleService.searchArticleByCondition(title, categroyId, tagId, isTop, startTime, endTime, page, count, userId);
    }
}