package com.wangchenglong.myblog.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.CapthaService;
import com.wangchenglong.myblog.service.impl.CaptchaServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
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
@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Resource
    CapthaService captchaService;

    @GetMapping("/captchaImage")
    private Result captchaImage(HttpServletRequest httpServletRequest) {

        return captchaService.getCaptha();
    }
}