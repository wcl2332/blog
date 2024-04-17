package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.TagMapper;
import com.wangchenglong.myblog.model.entity.ArticleTag;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.ArticleTagService;
import com.wangchenglong.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/2 16:29
 * @Description: TODO
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    TagMapper tagMapper;

    @Resource
    ArticleTagService articleTagService;

//    @Autowired
//    TagService tagService;

    @Override
    public Result listTags(Long userId) {
        LambdaQueryWrapper<Tag> listTagsQueryWrapper = new LambdaQueryWrapper();
        listTagsQueryWrapper.eq(Tag::getAuthorId, userId);
        List<Tag> tagList = tagMapper.selectList(listTagsQueryWrapper);
        return Result.success(tagList);
    }

    @Override
    public Result saveTag(String tagName, Long userId) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tag.setAuthorId(userId);
        int ins = tagMapper.insert(tag);
        if (ins <= 0) {
            return  Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(),ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        return Result.success("");
    }

    @Override
    public Result delTag(Long tagId,Long userId) {
        Tag tag = tagMapper.selectById(tagId);
        if (tag == null) {
            Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(),ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        LambdaQueryWrapper<Tag> tagQueryWrapper = new LambdaQueryWrapper();
        tagQueryWrapper.eq(Tag::getId, tagId);
        int delete = tagMapper.delete(tagQueryWrapper);
        if (delete <= 0) {
            return Result.fail(ErrorCode.DELETE_IS_ERROR.getCode(),ErrorCode.DELETE_IS_ERROR.getMsg());
        }
        return Result.success("");
    }
}