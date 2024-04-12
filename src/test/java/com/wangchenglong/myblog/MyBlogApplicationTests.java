package com.wangchenglong.myblog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangchenglong.myblog.mapper.ArticleMapper;
import com.wangchenglong.myblog.model.dto.ArticleDto;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MyBlogApplicationTests {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;

    @Test
    void contextLoads() {
    }

    @Test
    public void del() {
        List<Long> idlist = new ArrayList<>();
        idlist.add(Long.valueOf(121212));
        idlist.add(Long.valueOf(12313123));
        Integer deleteBatch = articleMapper.deleteBatch(idlist, Long.valueOf(2081681409));
        System.out.println(deleteBatch);
    }

    @Test
    public void findById() {
        Long articleId = Long.valueOf(1);
        Long userId = Long.valueOf(2081681409);
        Result result = articleService.findArticleById(articleId, userId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void list() {
        Result list = articleService.listArticles(1, 20, Long.valueOf(2081681409));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save() {
        ArticleDto articleDTO = new ArticleDto();
        articleDTO.setCategoryId(Long.valueOf(1));
        articleDTO.setContent("啥年份多少发发大发大搜非活动阿富汗ID哈 发的哈哈覅殴打hi佛的还 发货嗲话覅");
        articleDTO.setCoverImage("http://image.blog/a/b.png");
        articleDTO.setTitle("测试");
        articleDTO.setStatusCode(1);
        articleDTO.setWeight(1);
        Result result = articleService.saveArticle(articleDTO, Long.valueOf(2081681409));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
