package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.UserMapper;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.LoginService;
import com.wangchenglong.myblog.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 22:10
 * @Description: TODO
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    private final String KEY = "123456789_abcde_";

    @Override
    public Result<String> login(String account, String password, String imageUId, String imageCode) {
        Object result = redisTemplate.opsForValue().get(imageUId);
        if (result == null) {
            return Result.fail(ErrorCode.CAPTCHA_IS_NULL.getCode(), ErrorCode.CAPTCHA_IS_NULL.getMsg());
        }
        if (!result.toString().equals(imageCode)) {
            return Result.fail(ErrorCode.CAPTCHA_IS_ERROR.getCode(), ErrorCode.CAPTCHA_IS_ERROR.getMsg());
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, account);
        User user = new User();
        user = userMapper.selectOne(lambdaQueryWrapper);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        if (!user.getPassword().equals(DigestUtils.md5Hex(KEY + password))) {
            return Result.fail(ErrorCode.Login_PWD_ERROR.getCode(), ErrorCode.Login_PWD_ERROR.getMsg());
        }
        String token = JWTUtils.createToken(user);
        if (token != null) {
            LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            userLambdaUpdateWrapper.eq(User::getAccount, account).set(User::getLastTime, new Date());
            userMapper.update(user, userLambdaUpdateWrapper);
        }
        Boolean delete = redisTemplate.delete(imageUId);
        if (Boolean.FALSE.equals(delete)) {
            redisTemplate.delete(imageUId);
        }
        return Result.success(token);
    }
}