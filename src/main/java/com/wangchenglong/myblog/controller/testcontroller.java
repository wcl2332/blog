package com.wangchenglong.myblog.controller;

import com.wangchenglong.myblog.model.entity.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 20:08
 * @Description:
 */
@Api(tags = "测试管理")
@RestController
@RequestMapping("/test")
public class testcontroller {

    @ApiOperation("博客列表")
    @PostMapping("findAll")
    public void findAll(@RequestBody Article article){

    }
    @ApiOperation("博客列表")
    @PostMapping("/{name}")
    public void findAllByName(){
    }

    @GetMapping("/delete")
    public String del(@RequestParam("uid") String id) {
        System.out.println(id);
        int a = 10/0;
        return "";
    }
}