package com.cn.psychological.mapper;

import com.cn.psychological.entity.PNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 通知公告信息表 Mapper 接口
 * </p>
 */
public interface PNoticeMapper extends BaseMapper<PNotice> {

    List<PNotice> pageQuery(PNotice data);
}
