package center.helloworld.auth.service.impl;

import center.helloworld.auth.manager.SysPermissionMapper;
import center.helloworld.auth.manager.SysUserMapper;
import center.helloworld.common.core.entity.system.SystemUser;
import center.helloworld.common.core.utils.BCyptUtil;
import center.helloworld.common.entity.auth.AuthUser;
import center.helloworld.common.entity.system.SysUser;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/21 16:33
 */
@Service
public class WoguaUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        String authorization = request.getHeader("Authorization");
        SysUser sysUser = userMapper.findByMobile(mobile);
        if (sysUser != null) {
            String permissions = permissionMapper.findUserPermissions(sysUser.getId());
            boolean notLocked = false;
            if (sysUser.getLock().equals(0))
                notLocked = true;
            AuthUser authUser = new AuthUser(sysUser.getUsername(), sysUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            return transSystemUserToAuthUser(authUser,sysUser);
        } else {
            throw new UsernameNotFoundException("");
        }

    }
    private AuthUser transSystemUserToAuthUser(AuthUser authUser,SysUser systemUser) {
        authUser.setId(systemUser.getId());
        authUser.setUsername(systemUser.getUsername());
        authUser.setMail(systemUser.getMail());
        authUser.setMobile(systemUser.getMobile());
        authUser.setGender(systemUser.getGender());
        authUser.setLock(systemUser.getLock());
        authUser.setCreatetime(systemUser.getCreatetime());
        authUser.setUpdatetime(systemUser.getUpdatetime());
        return authUser;
    }

    public static void main(String[] args) {
        String[] passwordAndSalt = BCyptUtil.getPasswordAndSalt("123456");
        System.out.println(passwordAndSalt[0]);
    }
}
