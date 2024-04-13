package com.lxy.controller;


import com.lxy.mapper.TeachersMapper;
import com.lxy.result.Result;
import com.lxy.service.TeacherService;
import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.VO.TeacherPersonalClassCourseVO;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class TeacherController {

    private final TeacherService teacherService;
    private final TeachersMapper teachersMapper;

    @Autowired
    public TeacherController(TeacherService teacherService, TeachersMapper teachersMapper) {
        this.teacherService = teacherService;
        this.teachersMapper = teachersMapper;
    }


    @PostMapping("/teacher")
    public Teachers getTeacherByTeaCode(@RequestParam("tea_code") @NotBlank(message = "tea_code不能为空🙅‍") String tea_code) {

        return teacherService.getTeacherByTeaCode(tea_code);
    }

    @GetMapping("/teacherPersonalClassCourse/teacherId={teacherId}")
    public Result<List<TeacherPersonalClassCourseVO>> getTeacherClassCourseVO(@PathVariable(value = "teacherId") @NotBlank(message = "teacherId不能为空") String teacherId) {
        return Result.success(teachersMapper.getTeacherPersonalClassCourseVO(teacherId));
    }




}
