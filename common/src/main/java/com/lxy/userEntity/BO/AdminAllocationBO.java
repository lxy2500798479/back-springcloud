package com.lxy.userEntity.BO;


import com.lxy.userEntity.Classes;
import com.lxy.userEntity.Courses;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminAllocationBO implements Serializable {

    private final static long serialVersionUID = 1L;

    private long userId;
    List<Courses> courses;
    List<Classes> classes;
    private String operation;//add or delete

}
