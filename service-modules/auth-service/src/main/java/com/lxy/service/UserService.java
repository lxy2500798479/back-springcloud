package com.lxy.service;


import com.lxy.result.Result;
import com.lxy.userEntity.Students;
import com.lxy.vo.LoginVO;
import com.lxy.vo.TokenVO;

public interface UserService {


    Result<TokenVO> login(LoginVO loginVO);

    Result<TokenVO> refreshToken(String refreshToken);



}
