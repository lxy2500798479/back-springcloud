package com.lxy.userEntity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级作业关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassHomeworks implements Serializable {
    private Long id;

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 作业ID
     */
    private Long homeworkId;

    private static final long serialVersionUID = 1L;
}