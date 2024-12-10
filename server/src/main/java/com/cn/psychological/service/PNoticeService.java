package com.cn.psychological.service;

import com.cn.psychological.entity.PNotice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.psychological.utils.page.PageResult;

/**
 * <p>
 * 通知公告信息表 服务类
 * </p>
 */
public interface PNoticeService extends IService<PNotice> {

    PageResult pageQuery(int page, int limit, PNotice data);
}
