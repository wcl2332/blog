package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.entity.ExceptionLog;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;

public interface ExceptionLogService extends IService<ExceptionLog> {

    void saveExceptionLog(ExceptionLog exceptionLog);

    Result<PageVo<ExceptionLog>> searchExceptionLogList(Integer pageNo, Integer pageSize, Long userId);

    Result<ExceptionLog> getExceptionLogById(Long id, Long userId);
}
