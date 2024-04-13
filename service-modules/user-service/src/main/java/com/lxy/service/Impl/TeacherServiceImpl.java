package com.lxy.service.Impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxy.mapper.TeacherClassCoursesMapper;
import com.lxy.mapper.TeachersMapper;
import com.lxy.service.TeacherService;

import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.VO.AllocationStatisticsVO;
import com.lxy.userEntity.VO.TeacherAllocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl extends ServiceImpl<TeachersMapper, Teachers> implements TeacherService{

    @Autowired
    TeachersMapper teachersMapper;

    @Autowired
    TeacherClassCoursesMapper teacherClassCoursesMapper;

    @Override
    public Teachers getTeacherByTeaCode(String teaCode) {
        return teachersMapper.getTeacherByTeaCode(teaCode);
    }

    @Override
    public AllocationStatisticsVO getAllocationStatisticsVO() {
        AllocationStatisticsVO allocationStatisticsVO = new AllocationStatisticsVO();
        // 查询总老师数量
//        allocationStatisticsVO.setTotalTeachers(Math.toIntExact(teachersMapper.selectCount(null)));
        int totalTeachers = Math.toIntExact(teachersMapper.selectCount(null));
        allocationStatisticsVO.setTotalTeachers(totalTeachers);
        // 查询注册老师数量
        int registeredTeachers = Math.toIntExact(teachersMapper.selectCount(
                Wrappers.<Teachers>lambdaQuery()
                        .isNotNull(Teachers::getUserId)
        ));
        allocationStatisticsVO.setRegisteredTeachers(registeredTeachers);


        // 查询已分配老师数量
        int allocatedTeachers = teacherClassCoursesMapper.countAllocatedTeachers();


//        System.out.println("已经分配老师数量：" + allocatedTeachers);
        // 未分配老师数量
        allocationStatisticsVO.setUnallocatedTeachers(totalTeachers - allocatedTeachers);


        return allocationStatisticsVO;
    }

    @Override
    public List<TeacherAllocationVO> getTeacherAllocationVO() {
        return teachersMapper.getTeacherAllocationVO();
    }
}
