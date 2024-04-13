package com.lxy.vo;

import lombok.Data;
import reactor.util.annotation.Nullable;


import java.io.Serializable;


@Data
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String accountType;

    @Nullable
    private String rememberMe;

}
