package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.Homeworks;
import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.VO.TeacherAllocationVO;
import com.lxy.userEntity.VO.TeacherPersonalClassCourseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeachersMapper extends BaseMapper<Teachers> {

    List<TeacherAllocationVO> getTeacherAllocationVO();

    List<TeacherPersonalClassCourseVO> getTeacherPersonalClassCourseVO(String teacherId);


    int deleteByPrimaryKey(Integer teacherId);

    int insert(Teachers record);

    int insertSelective(Teachers record);

    Teachers selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(Teachers record);

    int updateByPrimaryKey(Teachers record);

    Teachers getTeacherByTeaCode(String teaCode);




}