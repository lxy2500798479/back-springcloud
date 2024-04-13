package com.lxy.userEntity.BO;

import com.lxy.userEntity.Admins;
import com.lxy.userEntity.Students;
import com.lxy.userEntity.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationBO {

    private String accountType;
    private Students students;
    private Teachers teachers;
    private Admins admins;


}
