package com.cn.psychological.service;

import com.cn.psychological.entity.PReply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.psychological.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 咨询信息表 服务类
 * </p>
 */
public interface PReplyService extends IService<PReply> {

    PageResult pageQuery(int page, int limit, PReply data);

    List<PReply> getList(PReply pReply);
}
