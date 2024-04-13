package com.lxy.controller;


import com.lxy.enums.ResultCodeEnum;
import com.lxy.result.Result;
import com.lxy.service.StudentService;
import com.lxy.userEntity.Students;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


@RestController
@Validated
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping("/student")
    public Result<Students> getStudentByStuId(@RequestParam @NotNull(message = "stu_id不能为空🙅‍") String stu_id) {
        Students student=studentService.getStudentByStuId(stu_id);
        if(student==null){
            return Result.fail(ResultCodeEnum.USER_NOT_EXISTS.getMessage());
        }
        return Result.success(student);

    }



}
