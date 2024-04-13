package com.lxy.userEntity;

import java.io.Serializable;
import lombok.Data;

/**
 * students
 */
@Data
public class Students  {
    private Integer id;

    private Long userId;

    private String stuId;

    private String stdName;

    private String idCard;

    private String classname;

    private String gender;

    private String phone;

    private String nation;

    private String politics;

    private Integer isschool;

//    private static final long serialVersionUID = 1L;
}