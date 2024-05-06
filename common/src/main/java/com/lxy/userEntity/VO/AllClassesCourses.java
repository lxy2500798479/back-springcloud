package com.lxy.userEntity.VO;

import com.lxy.userEntity.Classes;
import com.lxy.userEntity.Courses;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllClassesCourses implements Serializable {

    private List<Classes> classes;
    private List<Courses> courses;
    private static final long serialVersionUID = 1L;



}
