package com.lxy.mapper;

import com.lxy.userEntity.Permissions;

public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);
}