package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.entity.Category;
import com.wangchenglong.myblog.model.vo.CategoryVo;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;

import java.util.List;

public interface CategoryService extends IService<Category> {
    Result<String> addCategory(String categoryName, Long authorId);

    Result<String> deleteCategory(Long CategoryId, Long authorId);

    Result<PageVo<CategoryVo>> listCategory(Integer pageNum, Integer pageSize, Long authorId);

    Result<List<CategoryVo>> searchCategory(String keyword, Long authorId);
}
