package com.cn.psychological.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收藏信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PFavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 心理知识id
     */
    private String knowledgeId;

    /**
     * 收藏用户id
     */
    private String userId;

    /**
     * 收藏时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 存放收藏用户姓名
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 心理知识标题
     */
    @TableField(exist = false)
    private String knowledgeTitle;


    /**
     * 心理知识封面
     */
    @TableField(exist = false)
    private String image;



}
