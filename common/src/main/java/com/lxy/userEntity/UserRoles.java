package com.lxy.userEntity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles implements Serializable {
    private Long userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}