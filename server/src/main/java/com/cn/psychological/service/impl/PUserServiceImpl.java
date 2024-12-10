package com.cn.psychological.service.impl;

import com.cn.psychological.entity.PUser;
import com.cn.psychological.entity.PUser;
import com.cn.psychological.mapper.PUserMapper;
import com.cn.psychological.service.PUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.psychological.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 */
@Service
public class PUserServiceImpl extends ServiceImpl<PUserMapper, PUser> implements PUserService {

    @Override
    public PageResult pageQuery(int page, int limit, PUser data) {
        PageHelper.startPage(page, limit);
        List<PUser> queryList = baseMapper.pageQuery(data);
        PageInfo<PUser> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
