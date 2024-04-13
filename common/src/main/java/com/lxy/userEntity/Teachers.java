package com.lxy.userEntity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teachers implements Serializable {
    /**
     * 教师ID，作为主键
     */

    @TableId(type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 关联的用户ID
     */
    private Long userId;

    /**
     * 教师编码
     */
    private String teacherCode;

    /**
     * 教师姓名
     */
    private String teacherName;

    /**
     * 教师所在部门
     */
    private String department;

    /**
     * 教师身份证号
     */
    private String idCard;

    /**
     * 教师电话
     */
    private String phone;

    private static final long serialVersionUID = 1L;
}