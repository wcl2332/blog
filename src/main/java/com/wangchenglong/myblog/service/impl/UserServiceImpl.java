package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.UserMapper;
import com.wangchenglong.myblog.model.dto.UserDto;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.UserVo;
import com.wangchenglong.myblog.service.UserService;
import com.wangchenglong.myblog.utils.JWTUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 21:35
 * @Description: TODO
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    private final String KEY = "123456789_abcde_";

    @Override
    public Result getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_NOT_EXIST.getMsg());
        }
        UserVo userVO = new UserVo();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    @Override
    public Result updateUserInfo(UserDto userDTO, Long id) {
        User user = userMapper.selectById(id);
        BeanUtils.copyProperties(userDTO, user);
        //  System.out.println(user);
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getId, user.getId()).set(User::getRealName, user.getRealName())
                .set(User::getAvatar, user.getAvatar()).set(User::getSummary, user.getSummary());
        int update = userMapper.update(user, userLambdaUpdateWrapper);
        if (update <= 0) {
            return Result.fail(ErrorCode.UPDATE_IS_ERROR.getCode(), ErrorCode.UPDATE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result registerUser(String loginName, String password, String imageUId, String imageCode) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getAccount, loginName);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        if (user != null) {
            return new Result(ErrorCode.ACCOUNT_ALREADY_EXIST.getCode(), ErrorCode.ACCOUNT_ALREADY_EXIST.getMsg());
        }
        user = new User();
        user.setAccount(loginName);
        user.setPassword(DigestUtils.md5Hex(KEY + password));
        int p = userMapper.insert(user);
        if (p <= 0) {
            return Result.fail(ErrorCode.ACCOUNT_INSERT_FAIL.getCode(), ErrorCode.ACCOUNT_INSERT_FAIL.getMsg());
        }
        String token = JWTUtils.createToken(user);
        if (token != null) {
            LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            userLambdaUpdateWrapper.eq(User::getAccount, loginName).set(User::getLastTime, new Date());
            userMapper.update(user, userLambdaUpdateWrapper);
        }
        return Result.success(token);
    }
}