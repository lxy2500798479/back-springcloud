package com.lxy.mapper;

import com.lxy.userEntity.UserRoles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserRolesMapper {

    int insertUserRole(@Param("userId") Long userId, @Param("roleId") Integer roleId);


    int deleteByPrimaryKey(@Param("userId") Long userId, @Param("roleId") Integer roleId);

    int insert(UserRoles record);

    int insertSelective(UserRoles record);
}