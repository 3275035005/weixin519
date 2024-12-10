package com.cn.psychological.service.impl;

import com.cn.psychological.entity.PFavorite;
import com.cn.psychological.mapper.PFavoriteMapper;
import com.cn.psychological.service.PFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.psychological.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务实现类
 * </p>
 */
@Service
public class PFavoriteServiceImpl extends ServiceImpl<PFavoriteMapper, PFavorite> implements PFavoriteService {

    @Override
    public PageResult pageQuery(int page, int limit, PFavorite data) {
        PageHelper.startPage(page, limit);
        List<PFavorite> queryList = baseMapper.pageQuery(data);
        PageInfo<PFavorite> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<PFavorite> favoriteListByUserId(String userId) {
        return baseMapper.favoriteListByUserId(userId);
    }
}
