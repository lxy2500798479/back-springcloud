package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.userEntity.Students;




public interface StudentService extends IService<Students> {

//    Optional<Students> getStudentByStuId(String stu_id);
    Students getStudentByStuId(String stu_id);

}
