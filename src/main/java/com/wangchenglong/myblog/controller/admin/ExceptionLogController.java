package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.ExceptionLog;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.ExceptionLogService;
import com.wangchenglong.myblog.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/27 9:57
 * @Description: TODO
 */
@Api(tags = "管理端-系统监控")
@RestController
@RequestMapping("/sys")
public class ExceptionLogController {
    @Resource
    ExceptionLogService exceptionLogService;

    @ApiOperation("异常日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "页面显示个数")
    })
    @PostMapping("/getExLogList")
    public Result<PageVo<ExceptionLog>> getExLogList(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, HttpServletRequest request) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        String token = request.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return exceptionLogService.searchExceptionLogList(pageNum, pageSize, userId);
    }

    @ApiOperation("根据 id 获取异常日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "异常日志id")
    })
    @PostMapping("/getExLog")
    public Result<ExceptionLog> getExLogById(@RequestParam("id") Long id, HttpServletRequest request) {
        if (id == null || id < 1) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = request.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return exceptionLogService.getExceptionLogById(id, userId);
    }
}