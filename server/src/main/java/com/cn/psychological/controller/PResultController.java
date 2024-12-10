package com.cn.psychological.controller;

import com.cn.psychological.entity.PResult;
import com.cn.psychological.service.PResultService;
import com.cn.psychological.utils.page.PageResult;
import com.cn.psychological.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评测结果表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/p-result")
public class PResultController {

    @Autowired
    private PResultService service;

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
            @RequestBody PResult data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody PResult data){
        service.updateById(data);
        return R.ok();
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

    /**
     * 统计性格柱状图
     */
    @GetMapping("getTypeHistogram")
    public R getTypeHistogram(){
        List<Map<String, String>> map = service.getTypeHistogram();
        Map<String, Object> maps = new HashMap<>();

        List<String> type  = new ArrayList<>();
        List<Integer> number  = new ArrayList<>();
        for (Map<String, String> stringStringMap : map) {

            type.add(stringStringMap.get("type"));
            number.add(Integer.valueOf(String.valueOf(stringStringMap.get("number"))));
        }
        maps.put("type",type);
        maps.put("number",number);
        return R.ok().data("data", maps);
    }

}
