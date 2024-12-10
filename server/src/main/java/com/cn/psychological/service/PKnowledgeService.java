package com.cn.psychological.service;

import com.cn.psychological.entity.PKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.psychological.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 心理知识信息表 服务类
 * </p>
 */
public interface PKnowledgeService extends IService<PKnowledge> {

    PageResult pageQuery(int page, int limit, PKnowledge data);

    List<PKnowledge> getList();


    PKnowledge getOneById(String id);
}
