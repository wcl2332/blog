package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {
    Result<Object> listTags(Long userId);

    Result<Object> saveTag(String tagName, Long userId);

    Result<Object> delTag(Long tagId, Long userId);

    Result<PageVo<TagVo>> getTags(Long userId, Integer pageNum, Integer pageSize);

    Result<String> deleteTags(Long [] tagIds, Long userId);
}
