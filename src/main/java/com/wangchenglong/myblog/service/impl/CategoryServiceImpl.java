package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.CategoryMapper;
import com.wangchenglong.myblog.model.entity.Category;
import com.wangchenglong.myblog.model.vo.CategoryVo;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.CategoryService;
import com.wangchenglong.myblog.utils.CopyProperties;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/9 14:20
 * @Description:
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    CopyProperties copyProperties;

    @Override
    public Result<String> addCategory(String categoryName, Long authorId) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>().eq(Category::getName, categoryName).eq(Category::getAuthorId, authorId);
        Category category = categoryMapper.selectOne(wrapper);
        if (category != null) {
            return Result.fail(ErrorCode.PARAMS_IS_HAVE.PARAMS_TAG_IS_NULL.getCode(), ErrorCode.PARAMS_IS_HAVE.getMsg());
        }
        category = new Category();
        category.setName(categoryName);
        category.setAuthorId(authorId);
        int insert = categoryMapper.insert(category);
        if (insert <= 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<String> deleteCategory(Long CategoryId, Long authorId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId, CategoryId).eq(Category::getAuthorId, authorId);
        int delete = categoryMapper.delete(queryWrapper);
        if (delete <= 0) {
            return Result.fail(ErrorCode.DELETE_IS_ERROR.getCode(), ErrorCode.DELETE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<PageVo<CategoryVo>> listCategory(Integer pageNum, Integer pageSize, Long authorId, String keyWord) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(keyWord)) {
            queryWrapper.like(Category::getName, keyWord).eq(Category::getAuthorId, authorId).orderByDesc(Category::getCreateTime);
        } else {
            queryWrapper.eq(Category::getAuthorId, authorId).orderByDesc(Category::getCreateTime);
        }
        Page<Category> categoryPage = categoryMapper.selectPage(new Page<Category>(pageNum, pageSize), queryWrapper);
        List<Category> records = categoryPage.getRecords();
        List<CategoryVo> categories = copyProperties.copyList(records, CategoryVo.class);
        PageVo<CategoryVo> pageVo = new PageVo<>();
        pageVo.setTotal(categoryPage.getTotal());
        pageVo.setSize(categoryPage.getSize());
        pageVo.setCurrent(categoryPage.getCurrent());
        pageVo.setPages(categoryPage.getPages());
        pageVo.setRecords(categories);
        return Result.success(pageVo);
    }

    @Override
    public Result<List<CategoryVo>> searchCategory(String keyword, Long authorId) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<Category>();
        if (!Objects.isNull(keyword)) {
            wrapper.like(Category::getName, keyword).eq(Category::getAuthorId, authorId);
        } else {
            wrapper.eq(Category::getAuthorId, authorId);
        }
        List<Category> categories = categoryMapper.selectList(wrapper);
        List<CategoryVo> categoryVos = copyProperties.copyList(categories, CategoryVo.class);
        return Result.success(categoryVos);
    }
}