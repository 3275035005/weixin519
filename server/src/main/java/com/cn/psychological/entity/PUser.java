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
 * 用户信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码(MD5加密)
     */
    private String password;

    /**
     * 账号状态（1正常，2禁用）
     */
    private String status;

    /**
     * 姓名
     */
    private String name;

    /**
     * 权限（1代表管理员，2 代表心理咨询师 3代表用户）
     */
    private String userType;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别 (0女·， 1男)
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 介绍
     */
    private String content;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 接收原密码
     */
    @TableField(exist = false)
    private String oldPassword;

    @TableField(exist = false)
    private String[] roles;


}
