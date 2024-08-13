package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.TagVo;
import com.wangchenglong.myblog.service.TagService;
import com.wangchenglong.myblog.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/4 10:59
 * @Description: TODO
 */
@Api(tags = "管理端-文章标签相关")
@RestController
@RequestMapping("/admin/api/tag/")
public class TagController {
    @Resource
    TagService tagService;

    @ApiOperation("标签列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "显示个数")
    })
    public Result<PageVo<TagVo>> listTags(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, HttpServletRequest httpServletRequest) {
        if (pageNum == null || pageSize == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.getTags(userId, pageNum, pageSize);
    }

    @ApiOperation("新增标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagName", value = "标签的名字")
    })
    @PostMapping("/add")
    public Result<Object> saveTag(@RequestParam("tagName") String tagName, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(tagName)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.saveTag(tagName, userId);
    }

    @ApiOperation("删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", value = "标签的id")
    })
    @PostMapping("/delete")
    public Result<Object> deleteTag(@RequestParam("tagId") Long tagId, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(tagId)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.delTag(tagId, userId);
    }

    @ApiOperation("批量删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "标签的ids,选定多个id时，eg:  12,13")
    })
    @PostMapping("/deleteBatch")
    public Result<String> deleteBatchByTagIds(@RequestParam("ids") Long[] ids, HttpServletRequest httpServletRequest) {
        if (ids.length == 0) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.deleteTags(ids, userId);
    }

    @ApiOperation("查询标签（模糊查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagName" ,value = "标签名关键字")
    })
    @PostMapping("/searchTag")
    public Result<List<TagVo>> searchTag(@RequestParam("tagName") String tagName, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(tagName)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.searchTag(tagName, userId);
    }
}