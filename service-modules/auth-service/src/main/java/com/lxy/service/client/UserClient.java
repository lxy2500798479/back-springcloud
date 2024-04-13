package com.lxy.service.client;


import com.lxy.result.Result;
import com.lxy.userEntity.Admins;
import com.lxy.userEntity.BO.UserRegistrationBO;
import com.lxy.userEntity.Students;
import com.lxy.userEntity.Teachers;
import com.lxy.userEntity.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "user-service")
public interface UserClient {


//    这里的返回值包了一个Result的意义主要看使用 使用的话主要是取出具体的值 如果包了Result获取的时候就要使用getData()方法

    @PostMapping("/user/student")
    Result<Students> getStudentByStuId(@RequestParam("stu_id") String stu_id);

    @PostMapping("/user/teacher")
    Teachers getTeacherByTeaCode(@RequestParam("tea_code") String tea_code);


    @PostMapping("/user/register")
    boolean registerUser(@RequestBody UserRegistrationBO userRegistrationBO);

    @PostMapping("/user/selectByName")
    Users selectByName(@RequestParam("username") String username);

    @PostMapping("/user/admin")
    Result<Admins> getAdminByName(@RequestParam("admin_name") String admin_name);




}
