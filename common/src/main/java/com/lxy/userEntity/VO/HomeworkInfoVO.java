package com.lxy.userEntity.VO;


import com.lxy.userEntity.Classes;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class HomeworkInfoVO implements Serializable {

    private String Id;
    private String title;
    private String description;
    private Date create_at;
    private Date due_date;
    private int courseId;
    private String courseName;
    private List<Classes> classesList;


    @Serial
    private static final long serialVersionUID = 1L;
}
