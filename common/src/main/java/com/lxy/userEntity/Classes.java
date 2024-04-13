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
public class Classes implements Serializable {
    @TableId(value = "class_id",type = IdType.AUTO)
    private Integer classId;

    private String className;

    private static final long serialVersionUID = 1L;
}