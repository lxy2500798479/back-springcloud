package com.lxy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxy.userEntity.BO.UserRegistrationBO;
import com.lxy.userEntity.Users;



public interface UserService extends IService<Users> {

    Users getUserByUserName(String user_id);

    boolean register(UserRegistrationBO userRegistrationBO);


}
