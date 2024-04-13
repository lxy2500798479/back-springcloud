package com.lxy.controller;

import com.lxy.vo.LoginVO;
import com.lxy.result.Result;
import com.lxy.service.UserService;
import com.lxy.vo.TokenVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;




@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {


    private final UserService userService;


    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello123456");
        return "hello";
    }


    @PostMapping("/login")
    public Result<TokenVO> login(@RequestBody LoginVO loginVO){

        if(loginVO.getUsername()==null || loginVO.getPassword()==null){
            return Result.fail("用户名或密码不能为空");
        }


        if (loginVO.getRememberMe()==null){
            loginVO.setRememberMe("false");
        }

        try {
            return userService.login(loginVO);
        }catch (Exception e){
            log.error("登录失败：",e);
            return Result.fail("登录失败"+e.getMessage());
        }

    }

    @PostMapping("refreshToken")
    public Result<TokenVO> refreshToken(@RequestParam String refreshToken){

        try {
            return userService.refreshToken(refreshToken);
        }catch (Exception e){
            log.error("刷新token失败：",e);
            return Result.fail("刷新token失败"+e.getMessage());
        }


    }

}
