package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.dto.ArticleDto;
import com.wangchenglong.myblog.model.dto.ArticleUpdateDto;
import com.wangchenglong.myblog.model.entity.Article;
import com.wangchenglong.myblog.model.vo.Result;

import java.util.List;

public interface ArticleService extends IService<Article> {

    Result deleteArticleById(Long articleId, Long userId);

    Result deleteArticlesBatch(List<Long> idlist, Long userId);

    Result findArticleById(Long articleId, Long userId);

    Result listArticles(Integer page, Integer count, Long userId);

    Result saveArticle(ArticleDto articleDTO, Long userId);

    Result updateArticleWeight(Long articleId, Long userId);

    Result updateArticle(ArticleUpdateDto articleDTO, Long userId);

    Result searchArticle(String keyWord,Integer page,Integer count,Long userId);
}
