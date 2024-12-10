package com.cn.psychological.controller;


import com.cn.psychological.entity.PKnowledge;
import com.cn.psychological.service.PKnowledgeService;
import com.cn.psychological.utils.page.PageResult;
import com.cn.psychological.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 心理知识信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/p-knowledge")
public class PKnowledgeController {

    @Autowired
    private PKnowledgeService service;

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
            @RequestBody PKnowledge data){

        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody PKnowledge data){
        service.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  PKnowledge data){
        service.save(data);
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
     * 查询所有
     * @return
     */
    @GetMapping("getKnowledgeAll")
    public R getKnowledgeAll(){
        List<PKnowledge> list = service.list(null);
        return R.ok().data("row", list);
    }
}

