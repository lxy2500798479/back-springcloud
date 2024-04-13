package com.lxy.userEntity.VO;

import com.lxy.userEntity.Classes;
import com.lxy.userEntity.Courses;
import lombok.Data;



@Data
public class TeacherPersonalClassCourseVO {
    Classes classes;
    Courses courses;
}
