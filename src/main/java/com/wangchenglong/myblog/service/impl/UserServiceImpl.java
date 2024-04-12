package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.UserMapper;
import com.wangchenglong.myblog.model.dto.UserDto;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.UserVo;
import com.wangchenglong.myblog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 21:35
 * @Description: TODO
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

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
        System.out.println(user);
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getId, user.getId()).set(User::getRealName, user.getRealName())
                .set(User::getAvatar, user.getAvatar()).set(User::getSummary, user.getSummary());
        int update = userMapper.update(user, userLambdaUpdateWrapper);
        if (update <= 0) {
            return Result.fail(ErrorCode.UPDATE_IS_ERROR.getCode(), ErrorCode.UPDATE_IS_ERROR.getMsg());
        }
        return Result.success("");
    }
}