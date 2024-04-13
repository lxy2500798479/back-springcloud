package com.lxy.userEntity.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenusVO implements Serializable {
    private Integer menuId;

    private Integer parentId;

    private String menuName;

    private String path;

    private String component;

    private Integer order;

    private String icon;

    private List<MenusVO> children;

    private static final long serialVersionUID = 1L;
}
