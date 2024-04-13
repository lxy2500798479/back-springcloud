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
public class Courses implements Serializable {
    @TableId(value = "course_id",type= IdType.AUTO)
    private Integer courseId;

    private String courseName;

    private static final long serialVersionUID = 1L;
}