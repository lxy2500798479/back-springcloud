package com.lxy.config.shiro;

import com.lxy.service.client.UserClient;
import com.lxy.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {



        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


//        JwtToken jwtToken=(JwtToken)authenticationToken;
//        System.out.println(jwtToken.getToken()+"你好");
//        String studentId=extractStudentIdFromToken(jwtToken.getToken());
//        Result<Students> result=studentClient.getStudentByStuId(studentId);
//        Students student=result.getData();
//        if (student!=null){
//            return new SimpleAuthenticationInfo(student,student.getStuId(),getName());
//        }
        return  null;

//        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
//        Students student=studentClient.getStudentByStuId(token.getUsername());
//        if (student!=null){
//            return new SimpleAuthenticationInfo(student,student.getStuId(),getName());
//        }
//        return  null;
    }

    public String extractStudentIdFromToken(String token){
        Claims claims = JwtUtils.parseToken(token);
        System.out.println(claims.get("sub").toString());
        return claims.get("sub").toString();
    }
}
