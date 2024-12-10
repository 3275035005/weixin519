package com.cn.psychological.controller;


import com.cn.psychological.entity.PFavorite;
import com.cn.psychological.service.PFavoriteService;
import com.cn.psychological.utils.page.PageResult;
import com.cn.psychological.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收藏信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/p-favorite")
public class PFavoriteController {


    @Autowired
    private PFavoriteService service;

    /**
     * 分页条件查询
     * @param page   当前页码
     * @param limit 每页的大小
     * @param data 封装查询条件数据
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public R getPageData(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestBody PFavorite data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable String id){
        service.removeById(id);
        return R.ok();
    }
}

