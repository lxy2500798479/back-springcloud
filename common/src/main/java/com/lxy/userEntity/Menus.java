package com.lxy.userEntity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menus implements Serializable {
    private Integer menuId;

    private Integer parentId;

    private String menuName;

    private String path;

    private String component;

    private Integer order;

    private String icon;

    private static final long serialVersionUID = 1L;
}