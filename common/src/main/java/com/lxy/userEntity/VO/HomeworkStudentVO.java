package com.lxy.userEntity.VO;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class HomeworkStudentVO implements Serializable {
    String homeworkId;
    String title;
    String description;
    LocalDateTime createAt;
    LocalDateTime dueDate;
    String courseName;
    String teacherId;
    String teacherName;
    String fileName;
    long fileSize;

}
