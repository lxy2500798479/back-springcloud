package com.lxy.controller;



import com.lxy.mapper.UsersMapper;
import com.lxy.result.Result;
import com.lxy.service.UserService;

import com.lxy.userEntity.BO.UserRegistrationBO;
import com.lxy.userEntity.Users;
import com.lxy.userEntity.VO.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UsersMapper usersMapper;

//    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.redis.host}")
    private String redisHost;

    @GetMapping("/test")
    public String test(){
//        redisTemplate.opsForValue().set("test", "test");
////        return (String) redisTemplate.opsForValue().get("test");
        return redisHost;
    }


    @PostMapping("/register")
    public boolean registerUser(@RequestBody UserRegistrationBO userRegistrationBO){
        return userService.register(userRegistrationBO);
    }

    @PostMapping("/selectByName")
    public Users selectByName(@RequestParam String username){
        return userService.getUserByUserName(username);
    }


    @PostMapping("/userInfo")
    public Result<UserInfoVO> userInfo(@RequestParam String username){
        return Result.success(usersMapper.selectUserWithDetails(username));
    }

}
