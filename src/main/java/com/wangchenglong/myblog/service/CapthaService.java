package com.wangchenglong.myblog.service;

import com.wangchenglong.myblog.model.vo.CaptchaVo;
import com.wangchenglong.myblog.model.vo.Result;

public interface CapthaService {

    Result<CaptchaVo> getCaptha();
}
