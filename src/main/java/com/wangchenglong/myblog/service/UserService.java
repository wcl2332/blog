package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.dto.UserDto;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.UserVo;

public interface UserService extends IService<User> {

    Result<UserVo> getUserById(Long id);

    Result<Object> updateUserInfo(UserDto userDTO, Long id);

    Result<String> registerUser(String loginName, String password, String imageUId, String imageCode,String nickName);
}
