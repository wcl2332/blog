package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.model.entity.Server;
import com.wangchenglong.myblog.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.util.ServerInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/21 15:36
 * @Description: TODO
 */
@Api(tags = "管理端 - 系统监控")
@RestController
@RequestMapping("/sys")
public class SystemInfoController {

    @ApiOperation("服务器监控")
    @GetMapping("/systemInfo")
    public Result<Server> getServerInfo(HttpServletRequest servletRequest) {
        Server server = new Server();
        try {
            server.copyTo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(server);
    }
}