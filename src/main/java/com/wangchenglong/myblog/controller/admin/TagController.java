package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.TagService;
import com.wangchenglong.myblog.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public Result listTags(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.listTags(userId);
    }

    @ApiOperation("新增标签")
    @PostMapping("/add")
    public Result saveTag(@RequestParam("tagName") String tagName, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(tagName)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(),ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.saveTag(tagName,userId);
    }
    @ApiOperation("删除标签")
    @PostMapping("/delete")
    public Result deleteTag(@RequestParam("tagId") Long tagId, HttpServletRequest httpServletRequest) {
        if (Objects.isNull(tagId)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(),ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = httpServletRequest.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return tagService.delTag(tagId,userId);
    }
}