package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.Result;

public interface TagService extends IService<Tag> {
    Result listTags(Long userId);

    Result saveTag(String tagName,Long userId);

    Result delTag(Long userId);
}
