package com.cn.psychological.mapper;

import com.cn.psychological.entity.PUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 */
public interface PUserMapper extends BaseMapper<PUser> {

    List<PUser> pageQuery(PUser data);
}
