package com.cn.psychological.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.psychological.entity.PReply;
import com.cn.psychological.entity.PResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评测结果表 Mapper 接口
 * </p>
 */
public interface PResultMapper extends BaseMapper<PResult> {

    List<PResult> pageQuery(PResult pReply);

    List< Map<String, String>> getThisMonthDay(@Param("userId") String userId,@Param("thisMonth") String thisMonth);

    List<Map<String, String>> getTypeHistogram();

    Integer getScoreLimit0(@Param("userId")String userId);
}
