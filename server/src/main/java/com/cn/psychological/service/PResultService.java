package com.cn.psychological.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.psychological.entity.PResult;
import com.cn.psychological.utils.page.PageResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评测结果表 服务类
 * </p>
 */
public interface PResultService extends IService<PResult> {

    PageResult pageQuery(int page, int limit, PResult data);

    List< Map<String, String>> getThisMonthDay(String userId);

    List<Map<String, String>> getTypeHistogram();

    Integer getScoreLimit0(String userId);

}
