package com.lxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.lxy.result.Result;
import com.lxy.service.AdminService;
import com.lxy.service.ClassesService;
import com.lxy.service.CoursesService;
import com.lxy.service.TeacherService;
import com.lxy.userEntity.Admins;
import com.lxy.userEntity.BO.AdminAllocationBO;
import com.lxy.userEntity.Classes;
import com.lxy.userEntity.Courses;

import com.lxy.userEntity.VO.AdminAllocationTeachVO;
import com.lxy.userEntity.VO.AllClassesCourses;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {



    private final AdminService adminService;

    private final ClassesService classesService;

    private final CoursesService coursesService;


    private final TeacherService teacherService;


    @PostMapping("/admin")
    public Result<Admins> getAdminByName(String admin_name){
        QueryWrapper<Admins> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",admin_name);
        Admins admin = adminService.getOne(queryWrapper);
        if(admin==null){
            return Result.fail("管理员不存在");
        }
        return Result.success(admin);

    }


    @PostMapping("/getAllClassesCourses")
    public Result<AllClassesCourses> getAllClassesCourses(){
        AllClassesCourses allClassesCourses = new AllClassesCourses();
        List<Classes> classes = classesService.list();
        List<Courses> courses = coursesService.list();
        allClassesCourses.setClasses(classes);
        allClassesCourses.setCourses(courses);
        return Result.success(allClassesCourses);
    }

    @PostMapping("/getAllocationTeach")
    public Result<AdminAllocationTeachVO> getAllocationTeach(){
        AdminAllocationTeachVO adminAllocationTeachVO = new AdminAllocationTeachVO();
        adminAllocationTeachVO.setStatistics(teacherService.getAllocationStatisticsVO());
        adminAllocationTeachVO.setTeachers(teacherService.getTeacherAllocationVO());
        return Result.success(adminAllocationTeachVO);
    }


    @PostMapping("/addOrDelete")
    public Result<String> AdminAllocation(@RequestBody AdminAllocationBO adminAllocationBO){
        int result = adminService.AdminAddOrDeleteAllocation(adminAllocationBO);
        if(result > 0){
            switch(adminAllocationBO.getOperation()) {
                case "add":
                    return Result.success("添加成功");
                case "delete":
                    return Result.success("删除成功");
                default:
                    break;
            }
        }
        return Result.fail("操作失败");
    }











}
