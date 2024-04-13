package com.lxy.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;
    private String tokenHead;
    private TokenType tokenType;
    private String refreshToken;
    private String refreshTokenHead;
    private TokenType refreshTokenType;
    private long expireTime;

    // TokenType 枚举定义
    public enum TokenType {
        BEARER,
        MAC,
        // ... 其他 token 类型 ...
    }
}