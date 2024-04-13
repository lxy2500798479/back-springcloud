package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.Students;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StudentsMapper extends BaseMapper<Students> {

    Students getByStuId(String stu_id);



    int deleteByPrimaryKey(Integer id);

    int insert(Students record);

    int insertSelective(Students record);

    Students selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);
}