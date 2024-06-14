package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.HomeworkSubmissions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeworkSubmissionsMapper extends BaseMapper<HomeworkSubmissions> {
    int deleteByPrimaryKey(Long id);

    int insert(HomeworkSubmissions record);

    int insertSelective(HomeworkSubmissions record);

    HomeworkSubmissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HomeworkSubmissions record);

    int updateByPrimaryKey(HomeworkSubmissions record);
}