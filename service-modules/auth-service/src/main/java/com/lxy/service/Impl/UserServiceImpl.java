package com.lxy.service.Impl;

import com.lxy.result.Result;
import com.lxy.service.UserService;

import com.lxy.service.client.UserClient;
import com.lxy.tool.UserTool;
import com.lxy.userEntity.Admins;
import com.lxy.userEntity.BO.UserRegistrationBO;
import com.lxy.userEntity.Students;
import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.Users;
import com.lxy.utils.JwtUtils;
import com.lxy.vo.LoginVO;
import com.lxy.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserClient userClient;


    /**
     * 首先去users表查询数据，如果有数据，说明是用户，直接返回成功登录的结果
     * 如果不存在就去student表或者teacher表查询数据，如果有数据，说明是学生或者教师，就去注册用户
     */

    @Override
    public Result<TokenVO> login(LoginVO login) {
        // 尝试从用户表中获取用户
        Users users = userClient.selectByName(login.getUsername());
        if (users != null) {
            // 如果用户存在，检查密码
            if (UserTool.checkPassword(login.getPassword(), users.getPassword())) {
                // 密码匹配，返回成功登录的结果
                return Result.success(createTokenVO(JwtUtils.createJWT(login.getUsername(), login.getPassword())));
            } else {
                // 密码不匹配，返回失败信息
                return Result.fail("用户密码错误");
            }
        }

        // 用户不在用户表中，根据账户类型检查学生或教师表
        UserRegistrationBO userRegistrationBO = new UserRegistrationBO();
        if ("student".equals(login.getAccountType())) {
            return handleStudentLogin(login, userRegistrationBO);
        } else if ("teacher".equals(login.getAccountType())) {
            return handleTeacherLogin(login, userRegistrationBO);
        } else if ("admin".equals(login.getAccountType())) {
            return handleAdminLogin(login, userRegistrationBO);
        } else {
            return Result.fail("用户不存在或密码错误");
        }
    }

    private Result<TokenVO> handleAdminLogin(LoginVO login, UserRegistrationBO userRegistrationBO) {
        Admins admin = userClient.getAdminByName(login.getUsername()).getData();
        userRegistrationBO.setAccountType(login.getAccountType());
        userRegistrationBO.setAdmins(admin);
        if (admin == null || !admin.getPassword().equals(login.getPassword())) {
            return Result.fail("管理员用户不存在或密码错误");
        }
        System.out.println("admin:"+admin);
        System.out.println("userRegistrationBO:"+userRegistrationBO);
        userClient.registerUser(userRegistrationBO);
        return Result.success(createTokenVO(JwtUtils.createJWT(login.getUsername(), login.getPassword())));
    }
    private Result<TokenVO> handleStudentLogin(LoginVO login, UserRegistrationBO userRegistrationBO) {
        Students student = userClient.getStudentByStuId(login.getUsername()).getData();
        userRegistrationBO.setAccountType(login.getAccountType());
        userRegistrationBO.setStudents(student);
        if (student == null || !student.getIdCard().substring(12, 18).equals(login.getPassword())) {
            return Result.fail("学生用户不存在或密码错误");
        }
        userClient.registerUser(userRegistrationBO);
        return Result.success(createTokenVO(JwtUtils.createJWT(login.getUsername(), login.getPassword())));
    }
    private Result<TokenVO> handleTeacherLogin(LoginVO login, UserRegistrationBO userRegistrationBO) {
        Teachers teacher = userClient.getTeacherByTeaCode(login.getUsername());
        userRegistrationBO.setAccountType(login.getAccountType());
        userRegistrationBO.setTeachers(teacher);

        if (teacher == null || !teacher.getIdCard().substring(12, 18).equals(login.getPassword())) {
            return Result.fail("教师用户不存在或密码错误");
        }

        userClient.registerUser(userRegistrationBO);
        return Result.success(createTokenVO(JwtUtils.createJWT(login.getUsername(), login.getPassword())));
    }

    @Override
    public Result<TokenVO> refreshToken(String refreshToken) {
        if (!JwtUtils.isValidate(refreshToken)) {
            return Result.fail("refreshToken无效");
        }

        return Result.success(createTokenVO(refreshToken));
    }

    private TokenVO createTokenVO(String token) {
        return TokenVO.builder()
                .token(token)
                .refreshToken(JwtUtils.generateRefreshToken(token))
                .tokenType(TokenVO.TokenType.BEARER)
                .refreshTokenType(TokenVO.TokenType.BEARER)
                .build();
    }
}
