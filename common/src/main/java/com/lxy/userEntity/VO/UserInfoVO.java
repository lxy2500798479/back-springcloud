package com.lxy.userEntity.VO;




import com.lxy.userEntity.Permissions;
import com.lxy.userEntity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {

    String userId;
    String username;
    String uname;
    String avatar;
    List<Roles> roles;
    List<Permissions> permissions;
    List<MenusVO> menus;
}
