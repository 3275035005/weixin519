package com.cn.psychological.service.impl;

import com.cn.psychological.entity.PNotice;
import com.cn.psychological.mapper.PNoticeMapper;
import com.cn.psychological.service.PNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.psychological.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 通知公告信息表 服务实现类
 * </p>
 */
@Service
public class PNoticeServiceImpl extends ServiceImpl<PNoticeMapper, PNotice> implements PNoticeService {

    @Override
    public PageResult pageQuery(int page, int limit, PNotice data) {
        PageHelper.startPage(page, limit);
        List<PNotice> queryList = baseMapper.pageQuery(data);
        PageInfo<PNotice> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
