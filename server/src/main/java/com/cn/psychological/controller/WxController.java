package com.cn.psychological.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.psychological.entity.*;
import com.cn.psychological.service.*;
import com.cn.psychological.utils.response.R;
import com.cn.psychological.utils.utils.AceUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对微信端提供后端接口
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private PUserService pUserService;

    @Autowired
    private PNoticeService pNoticeService;

    @Autowired
    private PKnowledgeService pKnowledgeService;

    @Autowired
    private PFavoriteService pFavoriteService;

    @Autowired
    private PReplyService pReplyService;


    @Autowired
    private PResultService pResultService;

    /**
     * 登录功能
     */
    @PostMapping("login")
    public R login(@RequestBody PUser data){
        QueryWrapper<PUser> qw = new QueryWrapper<>();
        LambdaQueryWrapper<PUser> lambda = qw.lambda();
        lambda.eq(PUser::getUsername, data.getUsername());
        PUser pUser = pUserService.getOne(qw);
        if(pUser == null){
            return R.error("账号不存在");
        }
        String string2MD5Password = AceUtils.string2MD5(data.getPassword());
        // 判断密码
        if(!string2MD5Password.equals(pUser.getPassword())){
            return R.error("密码不正确");
        }
        if(!"3".equals(pUser.getUserType())){
            return R.error("请登录学生账号");
        }
        String status = pUser.getStatus();

        // 判断账号状态
        if(!"1".equals(status)){
            return R.error("账号已被禁用");
        }

        return R.ok().data("token",pUser.getId());
    }


    /**
     * 注册功能
     * @param data
     * @return
     */
    @PostMapping("register")
    public R register(@RequestBody PUser data){
        // MD5单向加密
        data.setPassword(AceUtils.string2MD5(data.getPassword()));
        data.setStatus("1");
        data.setUserType("3");
        pUserService.save(data);
        return R.ok();
    }


    /**
     * 查询所有通知公告
     */
    @GetMapping("getNotice")
    public R getNotice(){
        List<PNotice> noticeList = pNoticeService.list(new QueryWrapper<PNotice>().orderByAsc("sort"));
        return R.ok().data("row", noticeList);
    }

    /**
     * 通知公告详情查询
     */
    @GetMapping("getNoticeById")
    public R getNoticeById(String id){
        PNotice pNotice = pNoticeService.getById(id);
        return R.ok().data("row", pNotice);
    }

    /**
     * 查询所有心理知识信息
     */
    @GetMapping("getKnowledge")
    public R getKnowledge(){
        List<PKnowledge> knowledgeList = pKnowledgeService.getList();
        return R.ok().data("row", knowledgeList);
    }

    /**
     * 心理知识信息详情查询
     */
    @GetMapping("getKnowledgeById")
    public R getKnowledgeById(String id, String userId){
        PKnowledge pKnowledge = pKnowledgeService.getOneById(id);

        // 查看是否收藏了
        PFavorite favorite = pFavoriteService.getOne(new QueryWrapper<PFavorite>().eq("user_id", userId).eq("knowledge_id", id));
        pKnowledge.setFlag(favorite != null);
        return R.ok().data("row", pKnowledge);
    }


    /**
     * 查询所有咨询师信息
     */
    @GetMapping("getCounselor")
    public R getCounselor(){
        List<PUser> pUsers = pUserService.list(new QueryWrapper<PUser>().eq("user_type", "2").orderByDesc("create_time"));
        return R.ok().data("row", pUsers);
    }

    /**
     * 咨询师信息详情查询
     */
    @GetMapping("getCounselorById")
    public R getCounselorById(String id){
        PUser pUser = pUserService.getById(id);
        return R.ok().data("row", pUser);
    }

    /**
     * 在线咨询和匿名咨询聊天记录查询
     */
    @PostMapping("getReply")
    public R getReply(@RequestBody PReply pReply){
        List<PReply> list = pReplyService.getList(pReply);
        return R.ok().data("row", list);
    }

    /**
     * 在线咨询和匿名咨询发送聊天
     */
    @PostMapping("sendReply")
    public R sendReply(@RequestBody PReply pReply){
        pReply.setType("0");
        pReplyService.save(pReply);
        return R.ok();
    }


    /**
     * 密码修改
     */
    @PostMapping("updatePassword")
    public R updatePassword(@RequestBody PUser data){
        PUser pUser = pUserService.getById(data.getId());

        // 加密旧密码
        String oldPassword = AceUtils.string2MD5(data.getOldPassword());

        if(!oldPassword.equals(pUser.getPassword())){
            return R.error("旧密码不正确");
        }
        // 更新密码
        pUser.setPassword(AceUtils.string2MD5(data.getPassword()));
        pUserService.updateById(pUser);
        return R.ok();
    }

    /**
     * 个人信息查询
     */
    @GetMapping("getUserInfo")
    public R getUserInfo(String id){
        PUser ttUser = pUserService.getById(id);
        return R.ok().data("row", ttUser);
    }

    /**
     * 个人信息修改
     */
    @PostMapping("updateUserInfo")
    public R updateUserInfo(@RequestBody PUser pUser){
        QueryWrapper<PUser> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PUser> lambda = queryWrapper.lambda();

        lambda.eq(PUser::getId, pUser.getId());


        pUserService.update(pUser, lambda);
        return R.ok().data("row", pUser);
    }


    /**
     * 加入收藏
     */
    @PostMapping("appointmentFavorite")
    public R appointmentFavorite(@RequestBody PFavorite pFavorite){
        pFavoriteService.save(pFavorite);
        return R.ok();
    }

    /**
     * 取消收藏
     */
    @PostMapping("cancelAppointmentFavorite")
    public R cancelAppointmentFavorite(@RequestBody PFavorite pFavorite){
        QueryWrapper<PFavorite> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PFavorite> lambda = queryWrapper.lambda();
        lambda.eq(PFavorite::getKnowledgeId, pFavorite.getKnowledgeId());
        lambda.eq(PFavorite::getUserId, pFavorite.getUserId());
        pFavoriteService.remove(queryWrapper);
        return R.ok();
    }


    /**
     * 个人信息查询
     */
    @GetMapping("getHome")
    public R getHome(){
        Map<String, Object> map = new HashMap<>();
        map.put("notices",pNoticeService.list(new QueryWrapper<PNotice>().orderByAsc("sort")));
        map.put("knowledges",pKnowledgeService.list(new QueryWrapper<PKnowledge>().eq("status","1")));
        return R.ok().data("row", map);
    }

    /**
     * 提交评测
     */
    @PostMapping("sendResult/{userId}")
    public R sendResult(@PathVariable String userId, @RequestBody List<String> resultList){

        // 查询当前用户今天是否提交过评测
        PResult result = pResultService.getOne(new QueryWrapper<PResult>().eq("user_id", userId).last("and left(create_time, 10) = '" + AceUtils.getThisDay()+"'"));
        if(result != null){
            return R.error("今天已经提交过了");
        }
        int score = 0; // 分数

        Map<String, Integer> map = new HashMap<>();
        map.put("e",0);// 外向
        map.put("f",0);// 感觉
        map.put("i",0);// 内向
        map.put("n",0);// 直觉
        map.put("s",0);// 实感
        map.put("p",0);// 知觉
        map.put("t",0);// 思考
        map.put("j",0);// 判断

        for (int i = 0; i < resultList.size(); i++) {
            String ss = resultList.get(i);

            // MBTI性格统计
            if(i == 10){ //判断第一题
                if("0".equals(ss)){
                    map.put("e",  map.get("e")+1);
                }else{
                    map.put("i",  map.get("i")+1);
                }
            }else if(i == 11){
                if("0".equals(ss)){
                    map.put("e",  map.get("e")+1);
                }else{
                    map.put("i",  map.get("i")+1);
                }
            }else if(i == 12){
                if("0".equals(ss)){
                    map.put("e",  map.get("e")+1);
                }else{
                    map.put("i",  map.get("i")+1);
                }
            }else if(i == 13){
                if("0".equals(ss)){
                    map.put("s",  map.get("s")+1);
                }else{
                    map.put("n",  map.get("n")+1);
                }
            }else if(i == 14) {
                if("0".equals(ss)){
                    map.put("s",  map.get("s")+1);
                }else{
                    map.put("n",  map.get("n")+1);
                }
            }else if(i == 15) {
                if("0".equals(ss)){
                    map.put("t",  map.get("t")+1);
                }else{
                    map.put("f",  map.get("f")+1);
                }
            }else if(i == 16) {
                if("0".equals(ss)){
                    map.put("t",  map.get("t")+1);
                }else{
                    map.put("f",  map.get("f")+1);
                }
            }else if(i == 17) {
                if("0".equals(ss)){
                    map.put("j",  map.get("j")+1);
                }else{
                    map.put("p",  map.get("p")+1);
                }
            }else if(i == 18) {
                if("0".equals(ss)){
                    map.put("t",  map.get("t")+1);
                }else{
                    map.put("f",  map.get("f")+1);
                }
            }else if(i == 19) {
                if("0".equals(ss)){
                    map.put("j",  map.get("j")+1);
                }else{
                    map.put("p",  map.get("p")+1);
                }
            }
            if( i< 10){
                // 统计分数
                switch (ss){
                    case "0":
                        score+=10;
                        break;
                    case "1":
                        score+=8;
                        break;
                    case "2":
                        score+=6;
                        break;
                    case "3":
                        score+=4;
                    case "4":
                        score+=2;
                }
            }
        }



        PResult newResult = new PResult();
        newResult.setUserId(userId);
        newResult.setScore(score);
        String type = AceUtils.getMapOne(map);
        newResult.setType(type);
        pResultService.save(newResult);
        return R.ok().data("id",newResult.getId());
    }

    /**
     * 评测结果查询
     */
    @GetMapping("getResultById/{id}")
    public R getResultById(@PathVariable String id){
        PResult pResult = pResultService.getById(id);
        return R.ok().data("data",pResult);
    }

    /**
     * 查询当前用户所有评测结果
     */
    @GetMapping("getResultByUserId/{userId}")
    public R getResultByUserId(@PathVariable String userId){
        List<PResult> results = pResultService.list(new QueryWrapper<PResult>().eq("user_id", userId).orderByDesc("create_time"));
        return R.ok().data("data",results);
    }

    /**
     * 查询我的收藏
     */
    @GetMapping("appointmentFavoriteList/{userId}")
    public R appointmentFavoriteList(@PathVariable String userId){
        List<PFavorite> list = pFavoriteService.favoriteListByUserId(userId);
        return R.ok().data("row", list);
    }

    /**
     * 心理健康指数变化曲线
     */
    @GetMapping("getCurve/{userId}")
    public R getCurve(@PathVariable String userId){

       List< Map<String, String>> complaintMonth = pResultService.getThisMonthDay(userId);
        List<Integer> score = new ArrayList<>();
        List<String> day = new ArrayList<>();
        // 获取当年月份
        int month = AceUtils.getThisMonthDay();
        for(int i=1;i<=month;i++){
            score.add(0);
            day.add(i+"");
        }
        if(complaintMonth != null && complaintMonth.size()>0){
            for (Map<String, String> map : complaintMonth) {
                if(map != null){
                    int ss = Integer.parseInt(map.get("godate"));
                    score.remove(ss-1);
                    score.add(ss-1,Integer.valueOf(String.valueOf(map.get("score"))));
                }
            }
        }
        // 获取最后一次分数
        Integer scoreTwo = pResultService.getScoreLimit0(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("score",score);
        map.put("scoreTwo",scoreTwo);
        map.put("day",day);
        return R.ok().data("row", map);
    }
}
