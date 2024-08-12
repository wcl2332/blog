package com.wangchenglong.myblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangchenglong.myblog.model.dto.ArticleDto;
import com.wangchenglong.myblog.model.dto.ArticleUpdateDto;
import com.wangchenglong.myblog.model.entity.Article;
import com.wangchenglong.myblog.model.vo.ArticleVo;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;

import java.util.List;

public interface ArticleService extends IService<Article> {

    Result<Object> deleteArticleById(Long articleId, Long userId);

    Result<Object> deleteArticlesBatch(List<Long> idlist, Long userId);

    Result<ArticleVo> findArticleById(Long articleId, Long userId);

    Result<PageVo<ArticleVo>> listArticles(Integer page, Integer count, Long userId);

    Result<Object> saveArticle(ArticleDto articleDTO, Long userId);

    Result<Object> updateArticleWeight(Long articleId, Long userId);

    Result<Object> updateArticle(ArticleUpdateDto articleDTO, Long userId);

    Result<PageVo<ArticleVo>> searchArticle(String keyWord, Integer page, Integer count, Long userId);
}
