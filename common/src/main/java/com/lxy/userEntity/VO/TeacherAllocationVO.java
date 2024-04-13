package com.lxy.userEntity.VO;

import com.lxy.userEntity.Classes;
import com.lxy.userEntity.Courses;
import lombok.Data;

import java.util.List;

@Data
public class TeacherAllocationVO {

    private int teacherId;
    private String userId;
    private String teacherName;
    private List<Classes> classes; // 假设您有一个ClassVO来代表班级信息
    private List<Courses> courses; // 同理，CourseVO代表课程信息

}
