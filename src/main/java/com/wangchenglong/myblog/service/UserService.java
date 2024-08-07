package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.dto.UserDto;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;

public interface UserService extends IService<User> {

    Result getUserById(Long id);

    Result updateUserInfo(UserDto userDTO, Long id);

    Result registerUser(String loginName, String password, String imageUId, String imageCode);
}
