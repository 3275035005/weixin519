package com.cn.psychological.service.impl;

import com.cn.psychological.entity.PKnowledge;
import com.cn.psychological.entity.PUser;
import com.cn.psychological.mapper.PKnowledgeMapper;
import com.cn.psychological.mapper.PUserMapper;
import com.cn.psychological.service.PKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.psychological.service.PUserService;
import com.cn.psychological.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 心理知识信息表 服务实现类
 * </p>
 */
@Service
public class PKnowledgeServiceImpl extends ServiceImpl<PKnowledgeMapper, PKnowledge> implements PKnowledgeService {

    @Override
    public PageResult pageQuery(int page, int limit, PKnowledge data) {
        PageHelper.startPage(page, limit);
        List<PKnowledge> queryList = baseMapper.pageQuery(data);
        PageInfo<PKnowledge> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<PKnowledge> getList() {
        return baseMapper.getList();
    }

    @Override
    public PKnowledge getOneById(String id) {
        return baseMapper.getOneById(id);
    }
}
