package com.lxy.service.async;


import com.lxy.service.client.UserClient;
import com.lxy.userEntity.BO.UserRegistrationBO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class RegisterAsync {

    private final UserClient userClient;

    @Async
    public Future<Boolean> registerUser(UserRegistrationBO userRegistrationBO){

        boolean booleanUser = userClient.registerUser(userRegistrationBO);
        return new AsyncResult<>(booleanUser);


    }
}
