package com.lxy.userEntity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {
    /**
     * 用户ID，作为主键
     */
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户名，用于登录
     */
    private String username;

    /**
     * 用户密码，用于登录
     */
    private String password;

    private String uname;

    /**
     * 账户状态
     */
    private Object status;

    /**
     * 头像url
     */
    private String avatar;

    /**
     * 账户创建时间
     */
    private Date createdAt;

    /**
     * 账户最后更新时间
     */
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}