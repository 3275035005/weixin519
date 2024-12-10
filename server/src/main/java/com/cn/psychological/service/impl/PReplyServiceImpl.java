package com.cn.psychological.service.impl;

import com.cn.psychological.entity.PReply;
import com.cn.psychological.mapper.PReplyMapper;
import com.cn.psychological.service.PReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.psychological.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 咨询信息表 服务实现类
 * </p>
 */
@Service
public class PReplyServiceImpl extends ServiceImpl<PReplyMapper, PReply> implements PReplyService {

    @Override
    public PageResult pageQuery(int page, int limit, PReply data) {
        PageHelper.startPage(page, limit);
        List<PReply> queryList = baseMapper.pageQuery(data);
        PageInfo<PReply> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<PReply> getList(PReply pReply) {
        return baseMapper.getList(pReply);
    }
}
