package com.lxy.userEntity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherClassCourses implements Serializable {
    private Integer id;

    private Long userId;

    private Integer classId;

    private Integer courseId;

    private static final long serialVersionUID = 1L;
}