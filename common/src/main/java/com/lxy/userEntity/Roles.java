package com.lxy.userEntity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles implements Serializable {
    private Integer roleId;

    private String roleName;

    private String description;

    private static final long serialVersionUID = 1L;
}