package com.cn.psychological.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.psychological.entity.PReply;
import com.cn.psychological.entity.PUser;
import com.cn.psychological.service.PReplyService;
import com.cn.psychological.utils.page.PageResult;
import com.cn.psychological.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 咨询信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/p-reply")
public class PReplyController {

    @Autowired
    private PReplyService service;

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
            @RequestBody PReply data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 咨询回复
     * @param data
     * @return
     */
    @PostMapping("sendReply")
    public R sendReply(@RequestBody PReply data){
        data.setType("1");
        data.setCreateTime(null);
        data.setId(null);
        service.save(data);
        return R.ok();
    }


    /**
     * 查询咨询记录
     * @return
     */
    @GetMapping("getReply")
    public R getReply(String userId, String counselorId, String flag){
        PReply data = new PReply();
        data.setUserId(userId);
        data.setCounselorId(counselorId);
        data.setFlag(flag);
        List<PReply> list = service.getList(data);

        return R.ok().data("row", list);
    }

    /**
     * 删除操作
     * @param pReply
     * @return
     */
    @DeleteMapping("deleteById")
    public R deleteById(@RequestBody PReply pReply){
        QueryWrapper<PReply> qw = new QueryWrapper<>();
        LambdaQueryWrapper<PReply> lambda = qw.lambda();
        lambda.eq(PReply::getFlag, pReply.getFlag());
        lambda.eq(PReply::getCounselorId, pReply.getCounselorId());
        lambda.eq(PReply::getUserId, pReply.getUserId());
        service.remove(qw);
        return R.ok();
    }
}

