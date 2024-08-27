package com.wangchenglong.myblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangchenglong.myblog.mapper.ArticleTagMapper;
import com.wangchenglong.myblog.model.entity.ArticleTag;
import com.wangchenglong.myblog.service.ArticleService;
import com.wangchenglong.myblog.service.ArticleTagService;
import org.springframework.stereotype.Service;

/**
 * @Author: Wangchenglong
 * @Date: 2023/8/2 16:44
 * @Description:
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
}