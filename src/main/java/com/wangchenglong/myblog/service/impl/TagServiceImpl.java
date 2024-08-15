package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.TagMapper;
import com.wangchenglong.myblog.model.entity.ArticleTag;
import com.wangchenglong.myblog.model.entity.Tag;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.TagVo;
import com.wangchenglong.myblog.service.ArticleTagService;
import com.wangchenglong.myblog.service.TagService;
import com.wangchenglong.myblog.utils.CopyProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Resource
    CopyProperties copyProperties;


    @Override
    public Result<Object> listTags(Long userId) {
        LambdaQueryWrapper<Tag> listTagsQueryWrapper = new LambdaQueryWrapper();
        listTagsQueryWrapper.eq(Tag::getAuthorId, userId);
        List<Tag> tagList = tagMapper.selectList(listTagsQueryWrapper);
        return Result.success(tagList);
    }

    @Override
    public Result<Object> saveTag(String tagName, Long userId) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<Tag>().eq(Tag::getTagName, tagName).eq(Tag::getAuthorId, userId);
        Tag tag = tagMapper.selectOne(wrapper);
        if (!Objects.isNull(tag)) {
            return Result.fail(ErrorCode.PARAMS_IS_HAVE.getCode(), ErrorCode.PARAMS_IS_HAVE.getMsg());
        }
        tag = new Tag();
        tag.setTagName(tagName);
        tag.setAuthorId(userId);
        int ins = tagMapper.insert(tag);
        if (ins <= 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<Object> delTag(Long tagId, Long userId) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<Tag>().eq(Tag::getId, tagId).eq(Tag::getAuthorId, userId);
        Tag tag = tagMapper.selectOne(wrapper);
        if (Objects.isNull(tag)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        int delete = tagMapper.delete(wrapper);
        if (delete <= 0) {
            return Result.fail(ErrorCode.DELETE_IS_ERROR.getCode(), ErrorCode.DELETE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<PageVo<TagVo>> getTags(Long userId, Integer pageNum, Integer pageSize, String keyWord) {
        Page<Tag> tagPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Tag> tagQueryWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(keyWord)) {
            tagQueryWrapper.like(Tag::getTagName, keyWord).eq(Tag::getAuthorId, userId).orderByDesc(Tag::getCreateTime);
        } else {
            tagQueryWrapper.eq(Tag::getAuthorId, userId).orderByDesc(Tag::getCreateTime);
        }
        Page<Tag> tagPageInfo = tagMapper.selectPage(tagPage, tagQueryWrapper);
        PageVo<TagVo> pageVo = new PageVo<>();
        pageVo.setPages(tagPageInfo.getPages());
        pageVo.setTotal(tagPageInfo.getTotal());
        pageVo.setSize(tagPageInfo.getSize());
        pageVo.setCurrent(tagPageInfo.getCurrent());
        List<Tag> records = tagPageInfo.getRecords();
        List<TagVo> tagVos = copyProperties.copyList(records, TagVo.class);
        pageVo.setRecords(tagVos);
        return Result.success(pageVo);
    }

    @Override
    public Result<String> deleteTags(Long[] tagIds, Long userId) {
        int delete = tagMapper.delete(new LambdaQueryWrapper<Tag>().eq(Tag::getAuthorId, userId).in(Tag::getId, tagIds));
        if (delete <= 0) {
            return Result.fail(ErrorCode.DELETE_IS_ERROR.getCode(), ErrorCode.DELETE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<List<TagVo>> searchTag(String tagName, Long userId) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<Tag>().like(Tag::getTagName, tagName).eq(Tag::getAuthorId, userId);
        List<Tag> tags = tagMapper.selectList(wrapper);
        List<TagVo> tagVos = copyProperties.copyList(tags, TagVo.class);
        return Result.success(tagVos);
    }

}