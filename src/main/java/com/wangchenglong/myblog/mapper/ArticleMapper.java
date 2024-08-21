package com.wangchenglong.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangchenglong.myblog.model.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 8:51
 * @Description: TODO
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    
    Integer deleteBatch(List<Long> idlist, Long userId);

    Integer getMaxWeight();


}