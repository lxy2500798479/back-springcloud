package com.lxy.userEntity.VO;

import com.lxy.userEntity.Classes;
import com.lxy.userEntity.Courses;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllClassesCourses implements Serializable {

    List<Classes> classes;
    List<Courses> courses;
    private static final long serialVersionUID = 1L;



}
