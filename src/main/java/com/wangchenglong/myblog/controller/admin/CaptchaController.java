package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.model.vo.CaptchaVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.CapthaService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/7 10:09
 * @Description: TODO
 */
@Api(tags = "图片验证码")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Resource
    CapthaService captchaService;

    @GetMapping("/captchaImage")
    private Result<CaptchaVo> captchaImage(HttpServletRequest httpServletRequest) {

        return captchaService.getCaptha();
    }
}