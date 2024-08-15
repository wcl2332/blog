package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.mapper.*;
import com.wangchenglong.myblog.model.dto.ArticleDto;
import com.wangchenglong.myblog.model.dto.ArticleUpdateDto;
import com.wangchenglong.myblog.model.dto.TagDto;
import com.wangchenglong.myblog.model.entity.*;
import com.wangchenglong.myblog.model.vo.ArticleVo;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.model.vo.UserVo;
import com.wangchenglong.myblog.service.ArticleCategoryService;
import com.wangchenglong.myblog.service.ArticleService;
import com.wangchenglong.myblog.service.ArticleTagService;
import com.wangchenglong.myblog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/1 9:04
 * @Description: TODO
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    ArticleBodyMapper articleBodyMapper;
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    ArticleTagService articleTagService;
    @Resource
    TagService tagService;
    @Resource
    ArticleCategoryService articleCategoryService;

    @Override
    public Result<Object> deleteArticleById(Long articleId, Long userId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId, articleId).eq(Article::getAuthorId, userId);
        int delete = articleMapper.delete(lambdaQueryWrapper);
        if (delete <= 0) {
            return Result.fail(ErrorCode.DELETE_IS_ERROR.getCode(), ErrorCode.DELETE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<Object> deleteArticlesBatch(List<Long> idlist, Long userId) {
        Integer deleteBatch = articleMapper.deleteBatch(idlist, userId);
        if (deleteBatch <= 0) {
            return Result.fail(ErrorCode.DELETE_IS_ERROR.getCode(), ErrorCode.DELETE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<ArticleVo> findArticleById(Long articleId, Long userId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId, articleId).eq(Article::getAuthorId, userId);
        Article article = articleMapper.selectOne(lambdaQueryWrapper);
        if (article == null) {
            return Result.fail(ErrorCode.ARTICLE_IS_NULL.getCode(), ErrorCode.ARTICLE_IS_NULL.getMsg());
        }
        ArticleVo articleVO = new ArticleVo();
        BeanUtils.copyProperties(article, articleVO);
        //查出对应的 数据  封装到ArticleVO 中去
        //UserVO
        Long authorId = article.getAuthorId();
        User user = userMapper.selectById(authorId);
        UserVo userVO = new UserVo();
        BeanUtils.copyProperties(user, userVO);
        articleVO.setUser(userVO);
        // ArticleBody
        ArticleBody articleBody = articleBodyMapper.selectById(article.getContentId());
        articleVO.setArticleBody(articleBody);
        //Category
        Category category = categoryMapper.selectById(article.getCategoryId());
        articleVO.setCategory(category);
        //Tag
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, articleId);
        List<ArticleTag> articleTagList = articleTagService.list(articleTagLambdaQueryWrapper);
        List<Long> tagIds = new ArrayList<>();
        for (ArticleTag articleTag : articleTagList) {
            tagIds.add(articleTag.getTagId());
        }
        log.info("tagIds>>>{}", tagIds);
        List<Tag> tagList = new ArrayList<>();
        if (!tagIds.isEmpty()) {
            tagList = tagService.listByIds(tagIds);
        }
        articleVO.setTag(tagList);
        return Result.success(articleVO);
    }

    @Override
    public Result<PageVo<ArticleVo>> listArticles(Integer page, Integer count, Integer statusCode, Long userId) {
        Page<Article> articlePage = new Page<>(page, count);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (statusCode != null) {
            lambdaQueryWrapper.eq(Article::getStatusCode, 0).eq(Article::getAuthorId, userId).orderByDesc(Article::getWeight, Article::getCreateTime);
        } else {
            lambdaQueryWrapper.eq(Article::getAuthorId, userId).eq(Article::getStatusCode, 1).orderByDesc(Article::getWeight, Article::getCreateTime);
        }
        Page<Article> articlePageInfo = articleMapper.selectPage(articlePage, lambdaQueryWrapper);
        long total = articlePageInfo.getTotal();
        long current = articlePageInfo.getCurrent();
        long size = articlePageInfo.getSize();
        long pages = articlePageInfo.getPages();
        List<Article> articles = articlePageInfo.getRecords();
        List<ArticleVo> articleVoList = copyPropertiesList(articles);
        PageVo<ArticleVo> pageVo = new PageVo();
        pageVo.setCurrent(current);
        pageVo.setSize(size);
        pageVo.setTotal(total);
        pageVo.setPages(pages);
        pageVo.setRecords(articleVoList);
        return Result.success(pageVo);
    }

    @Override
    @Transactional
    public Result<Object> saveArticle(ArticleDto articleDTO, Long userId) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        article.setAuthorId(userId);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        //设置article内容
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleDTO.getContent());
        int insArticleBody = articleBodyMapper.insert(articleBody);
        if (insArticleBody <= 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        // insert 插入 article 信息
        Long articleBodyId = articleBody.getId();
        article.setContentId(articleBodyId);
        int ins = articleMapper.insert(article);
        if (ins <= 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        // insert 插入tag 标签信息
        String[] tagIds = articleDTO.getTagIds().split(",");
        List<Tag> tagList = tagService.listByIds(Arrays.asList(tagIds));
        List<ArticleTag> articleTagList = new ArrayList<>();
        for (Tag tag : tagList) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tag.getId());
            articleTag.setArticleId(article.getId());
            articleTag.setUserId(userId);
            articleTagList.add(articleTag);
        }
        // 批量插入 articleTagService
        boolean b = articleTagService.saveBatch(articleTagList);
        if (!b) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        //分类
        Long categoryId = articleDTO.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if (category == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setCategoryId(categoryId);
        articleCategory.setArticleId(article.getId());
        articleCategory.setUserId(userId);
        boolean save = articleCategoryService.save(articleCategory);
        if (!save) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<Object> updateArticleWeight(Long articleId, Long userId) {
        Article article = articleMapper.selectById(articleId);
        if (Objects.isNull(article)) {
            return Result.fail(ErrorCode.ARTICLE_IS_NULL.getCode(), ErrorCode.ARTICLE_IS_NULL.getMsg());
        }
        Integer weight = article.getWeight();
        if (weight == 0) {
            Integer maxWeight = articleMapper.getMaxWeight();
            article.setWeight(maxWeight + 1);
        } else if (weight > 0) {
            article.setWeight(0);
        }
        int update = articleMapper.updateById(article);
        if (update < 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        return Result.success();
    }

    @Override
    @Transactional
    public Result<Object> updateArticle(ArticleUpdateDto articleDTO, Long userId) {
        //1.判断文章是否存在
        Article article = articleMapper.selectById(articleDTO.getId());
        if (Objects.isNull(article)) {
            return Result.fail(ErrorCode.ARTICLE_IS_NULL.getCode(), ErrorCode.ARTICLE_IS_NULL.getMsg());
        }
        BeanUtils.copyProperties(articleDTO, article);
        article.setAuthorId(userId);
        article.setUpdateTime(new Date());
        //设置article内容
        ArticleBody articleBody = articleBodyMapper.selectById(article.getContentId());
        articleBody.setContent(articleDTO.getContent());
        int updArticleBody = articleBodyMapper.updateById(articleBody);
        if (updArticleBody <= 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        Long articleBodyId = articleBody.getId();
        article.setContentId(articleBodyId);
        int ins = articleMapper.updateById(article);
        if (ins <= 0) {
            return Result.fail(ErrorCode.INSERT_IS_FAILL.getCode(), ErrorCode.INSERT_IS_FAILL.getMsg());
        }
        // insert 插入tag 标签信息
        String[] tagIds = articleDTO.getTagIds().split(",");
        List<Tag> tagList = tagService.listByIds(Arrays.asList(tagIds));
        boolean remove = articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, articleDTO.getId()).in(ArticleTag::getTagId, tagIds));
        if (!remove) {
            return Result.fail(ErrorCode.PARAMS_UPDATE_IS_ERROR.getCode(), ErrorCode.PARAMS_UPDATE_IS_ERROR.getMsg());
        }
        List<ArticleTag> articleTagList = new ArrayList<>();
        for (Tag tag : tagList) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tag.getId());
            articleTag.setArticleId(article.getId());
            articleTag.setUserId(article.getAuthorId());
            articleTagList.add(articleTag);
        }
        // 批量插入 articleTagService
        boolean b = articleTagService.saveBatch(articleTagList);
        if (!b) {
            return Result.fail(ErrorCode.PARAMS_UPDATE_IS_ERROR.getCode(), ErrorCode.PARAMS_UPDATE_IS_ERROR.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result<PageVo<ArticleVo>> searchArticle(String keyWord, Integer page, Integer count, Long userId) {
        Page<Article> articlePage = new Page<>(page, count);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getAuthorId, userId).like(Article::getTitle, keyWord).orderByDesc(Article::getWeight, Article::getCreateTime);
        Page<Article> articlePageInfo = articleMapper.selectPage(articlePage, lambdaQueryWrapper);
        PageVo<ArticleVo> pageVO = new PageVo();
        pageVO.setPages(articlePageInfo.getPages());
        pageVO.setTotal(articlePageInfo.getTotal());
        pageVO.setCurrent(articlePageInfo.getCurrent());
        pageVO.setSize(articlePageInfo.getSize());
        List<Article> articles = articlePageInfo.getRecords();
        List<ArticleVo> articleVOList = copyPropertiesList(articles);
        pageVO.setRecords(articleVOList);
        return Result.success(pageVO);
    }

    public List<Tag> copytagDtoProertiesList(List<TagDto> list) {
        List<Tag> tagList = new ArrayList<>();
        for (TagDto tagDTO : list) {
            tagList.add(copyTag(tagDTO));
        }
        return tagList;
    }

    public Tag copyTag(TagDto tagDTO) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);
        return tag;
    }

    public List<ArticleVo> copyPropertiesList(List<Article> list) {
        List<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : list) {
            articleVos.add(copyPropertiesArticle(article));
        }
        return articleVos;
    }

    public ArticleVo copyPropertiesArticle(Article article) {
        ArticleVo articleVO = new ArticleVo();
        BeanUtils.copyProperties(article, articleVO);
        //查出对应的 数据  封装到ArticleVO 中去
        //UserVO
        Long authorId = article.getAuthorId();
        User user = userMapper.selectById(authorId);
        UserVo userVO = new UserVo();
        BeanUtils.copyProperties(user, userVO);
        articleVO.setUser(userVO);
        // ArticleBody
        ArticleBody articleBody = articleBodyMapper.selectById(article.getContentId());
        articleVO.setArticleBody(articleBody);
        //Category
        Category category = categoryMapper.selectById(article.getCategoryId());
        articleVO.setCategory(category);
        //Tag
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, article.getId());
        List<ArticleTag> articleTagList = articleTagService.list(articleTagLambdaQueryWrapper);
        List<Long> tagIds = new ArrayList<>();
        for (ArticleTag articleTag : articleTagList) {
            tagIds.add(articleTag.getTagId());
        }
        List<Tag> tagList = new ArrayList<>();
        if (!tagIds.isEmpty()) {
            tagList = tagService.listByIds(tagIds);
        }
        articleVO.setTag(tagList);
        return articleVO;
    }

}