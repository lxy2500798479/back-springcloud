package com.lxy.tool;


import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;

public class UserTool {
    /**
     * 加密密码
     *
     * @param password
     * @return
     */
    @NotNull
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 对比加密密码 成功返回true
     *
     * @param password
     * @param encryptedPassword
     * @return
     */
    public static boolean checkPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
