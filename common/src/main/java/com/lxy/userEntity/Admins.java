package com.lxy.userEntity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admins implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Date createdAt;

    private String adminName;

    private String avatar;

    private Long adminId;

    private static final long serialVersionUID = 1L;
}