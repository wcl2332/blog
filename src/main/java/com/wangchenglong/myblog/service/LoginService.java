package com.wangchenglong.myblog.service;

import com.wangchenglong.myblog.model.vo.Result;

public interface LoginService {

    Result<String> login(String account,String password, String  imageUID, String imageCode);

}
