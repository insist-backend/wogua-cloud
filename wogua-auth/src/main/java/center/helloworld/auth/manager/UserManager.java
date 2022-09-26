package center.helloworld.auth.manager;

import center.helloworld.auth.mapper.MenuMapper;
import center.helloworld.auth.mapper.UserMapper;
import center.helloworld.common.core.entity.system.Menu;
import center.helloworld.common.core.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        List<String> perms = new ArrayList<>();
        for (Menu m: userPermissions){
            perms.add(m.getPerms());
        }
        return StringUtils.join(perms, ",");
    }

    public static void main(String[] args) {
        String stalt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw("123456", stalt);
        System.out.println(hashpw);
    }
}
