package com.cn.psychological.mapper;

import com.cn.psychological.entity.PKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 心理知识信息表 Mapper 接口
 * </p>
 */
public interface PKnowledgeMapper extends BaseMapper<PKnowledge> {

    List<PKnowledge> pageQuery(PKnowledge data);

    List<PKnowledge> getList();

    PKnowledge getOneById(String id);
}
