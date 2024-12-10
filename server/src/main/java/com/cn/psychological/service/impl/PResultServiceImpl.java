package com.cn.psychological.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.psychological.entity.PResult;
import com.cn.psychological.mapper.PResultMapper;
import com.cn.psychological.service.PResultService;
import com.cn.psychological.utils.page.PageResult;
import com.cn.psychological.utils.utils.AceUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评测结果表 服务类
 * </p>
 */
@Service
public class PResultServiceImpl extends ServiceImpl<PResultMapper, PResult> implements PResultService {

    @Override
    public PageResult pageQuery(int page, int limit, PResult data) {
        PageHelper.startPage(page, limit);
        List<PResult> queryList = baseMapper.pageQuery(data);
        PageInfo<PResult> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List< Map<String, String>> getThisMonthDay(String userId) {

        return baseMapper.getThisMonthDay(userId, AceUtils.getThisMonth());
    }

    @Override
    public List<Map<String, String>> getTypeHistogram() {
        return baseMapper.getTypeHistogram();
    }

    @Override
    public Integer getScoreLimit0(String userId) {
        Integer score = baseMapper.getScoreLimit0(userId);
        return score == null? 0 : score;
    }
}
