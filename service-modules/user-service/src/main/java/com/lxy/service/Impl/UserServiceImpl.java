package com.lxy.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lxy.mapper.RolesMapper;
import com.lxy.mapper.UserRolesMapper;
import com.lxy.mapper.UsersMapper;
import com.lxy.service.UserService;
import com.lxy.tool.UserTool;
import com.lxy.userEntity.BO.UserRegistrationBO;
import com.lxy.userEntity.Roles;
import com.lxy.userEntity.Students;
import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UsersMapper,Users> implements UserService{


    private final UsersMapper usersMapper;

    private final UserRolesMapper userRolesMapper;

    private final RolesMapper rolesMapper;


    @Override
    public Users getUserByUserName(String username) {
      return usersMapper.selectAllByUsername(username);
    }



    @Transactional
    @Override
    public boolean register(UserRegistrationBO userRegistrationBO) {
        Users user = createUser(userRegistrationBO);
        user.setUserId(IdWorker.getId());
        String accountType = userRegistrationBO.getAccountType();



        String studentId = userRegistrationBO.getStudents() != null ? userRegistrationBO.getStudents().getStuId() : null;
        String teacherCode = userRegistrationBO.getTeachers() != null ? userRegistrationBO.getTeachers().getTeacherCode() : null;
        String adminId=userRegistrationBO.getAdmins()!=null?userRegistrationBO.getAdmins().getId().toString():null;

        int result = usersMapper.registerAndUpTeacherStudentAdmins(user, accountType, studentId, teacherCode,adminId);
        userRoleInsert(user.getUserId(),accountType);
        return result > 0;
    }

    public Users createUser(UserRegistrationBO userRegistrationBO) {
        Users user = new Users();
        switch (userRegistrationBO.getAccountType()) {
            case "student":
                Students student = userRegistrationBO.getStudents();
                if (student != null) {
                    user.setUsername(student.getStuId());
                    user.setUname(student.getStdName());
                    user.setPassword(UserTool.encryptPassword(student.getIdCard().substring(12, 18)));
                    user.setStatus("active");
                }
                break;
            case "teacher":
                Teachers teacher = userRegistrationBO.getTeachers();
                if (teacher != null) {
                    user.setUsername(teacher.getTeacherCode());
                    user.setUname(teacher.getTeacherName());
                    user.setPassword(UserTool.encryptPassword(teacher.getIdCard().substring(12, 18)));
                    user.setStatus("active");
                }

                break;
            case "admin":
                user.setUsername(userRegistrationBO.getAdmins().getUsername());
                user.setUname(userRegistrationBO.getAdmins().getAdminName());
                user.setPassword(UserTool.encryptPassword(userRegistrationBO.getAdmins().getPassword()));
                user.setStatus("active");

                break;
        }
        return user;
    }

    public void userRoleInsert(Long userId, String accountType) {


        List<Roles> roles=rolesMapper.getRoles();

        Roles role1=roles.stream()
                .filter(role-> role.getRoleName().equals(accountType))
                .findFirst()
                .orElse(null);

        if (role1!=null){
            userRolesMapper.insertUserRole(userId,role1.getRoleId());
        }



    }
}
