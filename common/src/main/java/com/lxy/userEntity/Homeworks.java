package com.lxy.userEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作业信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homeworks implements Serializable {
    private Long id;

    /**
     * 作业标题
     */
    private String title;

    /**
     * 作业描述
     */
    private String description;

    /**
     * 发布作业的教师ID
     */
    private Long teacherId;

    private Integer courseId;

    /**
     * 作业截止日期
     */
    private Date dueDate;

    /**
     * 作业创建时间
     */
    private Date createdAt;

    /**
     * 作业最后更新时间
     */
    private Date updatedAt;

    private String filePath;

    private String fileName;

    private Long fileSize;

    private String fileType;

    private String fileMd5;

    private String downloadUrl;

    private static final long serialVersionUID = 1L;
}