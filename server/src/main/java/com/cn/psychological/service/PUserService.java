package com.cn.psychological.service;

import com.cn.psychological.entity.PUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.psychological.utils.page.PageResult;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 */
public interface PUserService extends IService<PUser> {

    PageResult pageQuery(int page, int limit, PUser data);
}
