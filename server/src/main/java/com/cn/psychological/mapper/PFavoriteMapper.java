package com.cn.psychological.mapper;

import com.cn.psychological.entity.PFavorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收藏信息表 Mapper 接口
 * </p>
 */
public interface PFavoriteMapper extends BaseMapper<PFavorite> {

    List<PFavorite> pageQuery(PFavorite data);

    List<PFavorite> favoriteListByUserId(@Param("userId") String userId);
}
