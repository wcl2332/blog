package com.wangchenglong.myblog.service;

import com.wangchenglong.myblog.model.vo.Result;

public interface LoginService {

    Result login(String account,String password);

}
