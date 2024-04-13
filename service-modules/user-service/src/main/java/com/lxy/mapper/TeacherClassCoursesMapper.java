package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.TeacherClassCourses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherClassCoursesMapper extends BaseMapper<TeacherClassCourses> {


    @Select("SELECT COUNT(DISTINCT user_id) FROM teacher_class_courses")
    int countAllocatedTeachers();

    int insertBatch(@Param("list")List<TeacherClassCourses> list);

    int deleteBatch(@Param("list")List<TeacherClassCourses> list);




    int deleteByPrimaryKey(Integer id);

    int insert(TeacherClassCourses record);

    int insertSelective(TeacherClassCourses record);

    TeacherClassCourses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherClassCourses record);

    int updateByPrimaryKey(TeacherClassCourses record);
}