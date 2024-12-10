package com.cn.psychological.mapper;

import com.cn.psychological.entity.PReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 咨询信息表 Mapper 接口
 * </p>
 */
public interface PReplyMapper extends BaseMapper<PReply> {

    List<PReply> pageQuery(PReply pReply);

    List<PReply> getList(PReply pReply);

}
