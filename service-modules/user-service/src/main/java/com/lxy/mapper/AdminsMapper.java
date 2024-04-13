package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.Admins;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminsMapper extends BaseMapper<Admins>{
    int deleteByPrimaryKey(Integer id);

    int insert(Admins record);

    int insertSelective(Admins record);

    Admins selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admins record);

    int updateByPrimaryKey(Admins record);
}