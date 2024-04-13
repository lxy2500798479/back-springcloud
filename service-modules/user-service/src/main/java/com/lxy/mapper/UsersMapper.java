package com.lxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxy.userEntity.Users;
import com.lxy.userEntity.VO.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {
    int deleteByPrimaryKey(Long userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    UserInfoVO selectUserWithDetails(@Param("username") String username);

    Users selectAllByUsername(@Param("username") String username);

    int registerAndUpTeacherStudentAdmins(@Param("user") Users user, @Param("accountType") String accountType, @Param("studentId") String studentId, @Param("teacherCode") String teacherCode,@Param("adminId") String adminId);
}