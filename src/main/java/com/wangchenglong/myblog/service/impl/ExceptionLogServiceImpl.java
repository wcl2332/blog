package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.mapper.ExceptionLogMapper;
import com.wangchenglong.myblog.model.entity.ExceptionLog;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.ExceptionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/23 14:51
 * @Description:
 */
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {
    @Resource
    ExceptionLogMapper exceptionLogMapper;

    @Override
    public void saveExceptionLog(ExceptionLog exceptionLog) {
        exceptionLogMapper.insert(exceptionLog);
    }

    @Override
    public Result<PageVo<ExceptionLog>> searchExceptionLogList(Integer pageNo, Integer pageSize, Long userId) {
        Page<ExceptionLog> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<ExceptionLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ExceptionLog::getUserId, userId);
        Page<ExceptionLog> exceptionLogPage = exceptionLogMapper.selectPage(page, wrapper);
        List<ExceptionLog> records = exceptionLogPage.getRecords();
        long pages = exceptionLogPage.getPages();
        long total = exceptionLogPage.getTotal();
        long size = exceptionLogPage.getSize();
        long current = exceptionLogPage.getCurrent();
        PageVo<ExceptionLog> pageVo = new PageVo<>();
        pageVo.setPages(pages);
        pageVo.setTotal(total);
        pageVo.setRecords(records);
        pageVo.setSize(size);
        pageVo.setCurrent(current);
        return Result.success(pageVo);
    }

    @Override
    public Result<ExceptionLog> getExceptionLogById(Long id, Long userId) {
        LambdaQueryWrapper<ExceptionLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ExceptionLog::getId, id).eq(ExceptionLog::getUserId, userId);
        ExceptionLog exceptionLog = exceptionLogMapper.selectOne(wrapper);
        return Result.success(exceptionLog);
    }
}