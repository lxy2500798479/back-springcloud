package com.lxy.userEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作业提交信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkSubmissions implements Serializable {
    private Long id;

    /**
     * 关联的作业ID
     */
    private Long homeworkId;

    /**
     * 提交作业的学生ID
     */
    private Long studentId;

    /**
     * 提交文件的存储路径
     */
    private String submissionPath;

    private String fileName;

    private Long fileSize;

    private String fileType;


    /**
     * 提交时间，默认为当前时间
     */
    private Date submittedAt = new Date();

    /**
     * 提交状态，如果什么没有说明没 提交为1  再次+1
     */
    private Integer status;

    /**
     * 老师对作业的反馈
     */
    private String feedback;

    private String fileMd5;

    private static final long serialVersionUID = 1L;
}