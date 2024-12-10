package com.cn.psychological.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.psychological.entity.PUser;
import com.cn.psychological.service.PKnowledgeService;
import com.cn.psychological.service.PNoticeService;
import com.cn.psychological.service.PReplyService;
import com.cn.psychological.service.PUserService;
import com.cn.psychological.utils.page.PageResult;
import com.cn.psychological.utils.response.R;
import com.cn.psychological.utils.utils.AceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/p-user")
public class PUserController {

    @Autowired
    private PUserService service;

    @Autowired
    private PKnowledgeService pKnowledgeService;

    @Autowired
    private PNoticeService pNoticeService;

    @Autowired
    private PReplyService pReplyService;


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
            @RequestBody PUser data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }
    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody PUser data){
        service.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody PUser data){
        data.setUserType("3");
        // MD5单向加密
        data.setPassword(AceUtils.string2MD5(data.getPassword()));
        service.save(data);
        return R.ok();
    }

    /**
     * 重置密码 默认密码666666
     * @param id
     * @return
     */
    @PostMapping("resetPassword/{id}")
    public R resetPassword(@PathVariable String id){
        PUser pUser = service.getById(id);
        // MD5单向加密
        pUser.setPassword(AceUtils.string2MD5("666666"));
        service.updateById(pUser);
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
     * 查询所有用户信息
     * @return
     */
    @GetMapping("getUserAll")
    public R getUserAll(String userType){
        LambdaQueryWrapper<PUser> qw = new LambdaQueryWrapper<>();
        qw.eq(PUser::getUserType, userType);
        qw.orderByAsc(PUser::getCreateTime);
        List<PUser> list = service.list(qw);
        return R.ok().data("row", list);
    }

    /**
     * 修改密码
     */
    @PostMapping("updatePassword")
    public R updatePassword(@RequestBody PUser data){
        PUser pUser = service.getById(data.getId());

        // 加密旧密码
        String oldPassword = AceUtils.string2MD5(data.getOldPassword());

        if(!oldPassword.equals(pUser.getPassword())){
            return R.error("旧密码不正确");
        }
        // 更新密码
        pUser.setPassword(AceUtils.string2MD5(data.getPassword()));
        service.updateById(pUser);
        return R.ok();
    }

    /**
     * 通过用户id获取用户信息
     */
    @GetMapping("getUserInfo")
    public R getUserInfo(String userId){
        PUser pUser = service.getById(userId);
        return R.ok().data("data", pUser);
    }

    /**
     * 登录功能
     */
    @PostMapping("login")
    public R login(HttpServletRequest request, @RequestBody PUser data){
        QueryWrapper<PUser> qw = new QueryWrapper<>();
        LambdaQueryWrapper<PUser> lambda = qw.lambda();
        lambda.eq(PUser::getUsername, data.getUsername());
        PUser pUser = service.getOne(qw);
        if(pUser == null){
            return R.error("账号不存在");
        }
        String string2MD5Password = AceUtils.string2MD5(data.getPassword());
        System.out.println(string2MD5Password);
        // 判断密码
        if(!string2MD5Password.equals(pUser.getPassword())){
            return R.error("密码不正确");
        }

        String status = pUser.getStatus();

        // 判断账号状态
        if(!"1".equals(status)){
            return R.error("账号已被禁用");
        }

        if(!"2".equals(pUser.getUserType()) && !"1".equals(pUser.getUserType())){
            return R.error("账号不存在");
        }


        return R.ok().data("token",pUser.getId());
    }



    /**
     * 通过用户id获取信息
     * @return
     */
    @GetMapping("info")
    public R info(String token){
        PUser pUser = service.getById(token);
        if("1".equals(pUser.getUserType())){
            pUser.setRoles(new String[]{"admin"});
        }else if("2".equals(pUser.getUserType())){
            pUser.setRoles(new String[]{"doctor"});
        }
        return R.ok().data("data",pUser);
    }


    /**
     * 查询首页统计数据
     *      1、统计平台总用户数量
     *      2、统计平台心理知识数量
     *      3、统计平台累计咨询数量
     *      4、统计平台通知公告数量
     * @return
     */
    @GetMapping("getHome")
    public R getHome() {
        Map<String, Integer> map = new HashMap<>();
        int userCount = service.count(null);
        int knowledgeCount = pKnowledgeService.count(null);
        int replyCount = pReplyService.count(null);
        int noticeCount = pNoticeService.count(null);
        map.put("userCount",userCount);
        map.put("knowledgeCount",knowledgeCount);
        map.put("replyCount",replyCount);
        map.put("noticeCount",noticeCount);
        return R.ok().data("data",map);
    }
}

