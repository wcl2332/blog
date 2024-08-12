package com.wangchenglong.myblog.controller.admin;

import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.Category;
import com.wangchenglong.myblog.model.vo.CategoryVo;
import com.wangchenglong.myblog.model.vo.PageVo;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.CategoryService;
import com.wangchenglong.myblog.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Wangchenglong
 * @Date: 2024/8/9 14:33
 * @Description: TODO
 */
@Api(tags = "管理端-分类管理")
@RestController
@RequestMapping("/admin/api/category/")
public class CategroyController {
    @Resource
    CategoryService categoryService;

    @ApiOperation("新增分类")
    @ApiImplicitParam(name = "name", value = "分类名称")
    @PostMapping("/addCategory")
    public Result<String> saveCategory(@RequestParam("name") String categoryName, HttpServletRequest request) {
        if (StringUtils.isBlank(categoryName)) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = request.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return categoryService.addCategory(categoryName, userId);
    }

    @ApiOperation("删除分类")
    @ApiImplicitParam(name = "id", value = "分类ID")
    @PostMapping("/delete")
    public Result<String> deleteCategory(@RequestParam("id") Long id, HttpServletRequest request) {
        if (id == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = request.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return categoryService.deleteCategory(id, userId);
    }

    @ApiOperation("分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页面显示个数"),
            @ApiImplicitParam(name = "pageNum", value = "页码")
    })
    @GetMapping("/listPage")
    public Result<PageVo<CategoryVo>> listCategory(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum, HttpServletRequest request) {
        if (pageNum == null || pageNum == null) {
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(), ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        String token = request.getHeader("token");
        Long userId = JWTUtils.getUserInfo(token);
        return categoryService.listCategory(pageNum, pageSize, userId);
    }

}