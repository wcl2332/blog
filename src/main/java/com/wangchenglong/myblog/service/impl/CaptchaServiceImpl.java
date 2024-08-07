package com.wangchenglong.myblog.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.util.IdUtil;
import com.wangchenglong.myblog.model.vo.CaptchaVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.CapthaService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/7 14:38
 * @Description: TODO
 */
@Service
public class CaptchaServiceImpl implements CapthaService {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Result getCaptha() {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        String imageId = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(imageId, captcha.getCode());
        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setUid(imageId);
        captchaVo.setCaptchaCode(captcha.getImageBase64Data());
        return new Result().success(captchaVo);
    }


}