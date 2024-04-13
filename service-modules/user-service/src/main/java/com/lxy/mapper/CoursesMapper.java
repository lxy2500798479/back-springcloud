package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.Courses;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoursesMapper extends BaseMapper<Courses> {
    int deleteByPrimaryKey(Integer courseId);

    int insert(Courses record);

    int insertSelective(Courses record);

    Courses selectByPrimaryKey(Integer courseId);

    int updateByPrimaryKeySelective(Courses record);

    int updateByPrimaryKey(Courses record);
}