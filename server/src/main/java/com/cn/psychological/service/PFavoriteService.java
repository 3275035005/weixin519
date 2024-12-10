package com.cn.psychological.service;

import com.cn.psychological.entity.PFavorite;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.psychological.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务类
 * </p>
 */
public interface PFavoriteService extends IService<PFavorite> {

    PageResult pageQuery(int page, int limit, PFavorite data);

    List<PFavorite> favoriteListByUserId(String userId);
}
