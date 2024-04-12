package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        Long userId = httpServletRequest.getDateHeader("token");
        return tagService.listTags(userId);
    }

    @ApiOperation("新增标签")
    @PostMapping("/save")
    public Result saveTag(@RequestParam("tagName") String tagName, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(tagName)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(),ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        return tagService.saveTag(tagName,httpServletRequest.getDateHeader("token"));
    }
}