package com.lxy.mapper;

import com.lxy.userEntity.Roles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RolesMapper {

    List<Roles> getRoles();

    int deleteByPrimaryKey(Integer roleId);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}