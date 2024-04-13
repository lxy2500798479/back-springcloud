package com.lxy.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//    private static final String SECRET_STRING = "your-secret-string-hereqeqeqopjtqtttq4r6q5r.qwrqw5rq6rq4r6w"; // 确保这个密钥足够长和复杂
//    private static final Key SECRET_KEY = new SecretKeySpec(SECRET_STRING.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    //过期时间为1小时
    private static final long expirationTime =3600*1000*24*7;// 7天
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 3600 * 24 * 8 * 1000; // 7天

    public static String createJWT(String username, String password) {
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        return Jwts.builder()
                .setSubject(username)
                .claim("password", password)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)

                .compact();
    }
    public static String createJWT(String username, String password,long eTime) {
        Date expirationDate = new Date(System.currentTimeMillis() + eTime);
        return Jwts.builder()
                .setSubject(username)
                .claim("password", password)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public static String generateRefreshToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean isValidate(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expirationDate.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;  // 如果JWT过期
        } catch (Exception e) {
            return true;  // 其他异常情况（例如无效的JWT等）
        }
    }

    public static long getExpireTime(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getExpiration()
                .getTime();
    }
}
