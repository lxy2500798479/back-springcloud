package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.VO.AllocationStatisticsVO;
import com.lxy.userEntity.VO.TeacherAllocationVO;

import java.util.List;

public interface TeacherService extends IService<Teachers> {
    Teachers getTeacherByTeaCode(String teaCode);

    AllocationStatisticsVO getAllocationStatisticsVO();

    List<TeacherAllocationVO> getTeacherAllocationVO();
}
