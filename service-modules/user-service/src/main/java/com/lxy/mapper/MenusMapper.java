package com.lxy.mapper;

import com.lxy.userEntity.Menus;

public interface MenusMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
}