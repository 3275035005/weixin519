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
 * 咨询信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 咨询师id
     */
    private String counselorId;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 类型(0咨询 1回复)
     */
    private String type;

    /**
     * 标识(0在线咨询 1匿名咨询)
     */
    private String flag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    @TableField(exist = false)
    private String studentAvatar;


    @TableField(exist = false)
    private String counselorAvatar;

    @TableField(exist = false)
    private String studentName;


    @TableField(exist = false)
    private String counselorName;

}
