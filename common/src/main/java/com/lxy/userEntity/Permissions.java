package com.lxy.userEntity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissions implements Serializable {
    private Integer permissionId;

    private String permissionName;

    private String description;

    private static final long serialVersionUID = 1L;
}