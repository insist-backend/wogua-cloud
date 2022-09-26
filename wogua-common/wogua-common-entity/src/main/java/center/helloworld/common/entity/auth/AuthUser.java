package center.helloworld.common.entity.auth;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends User {

    private static final long serialVersionUID = -6411066541689297219L;

    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 性别
     */
    private Integer gender = 1;

    /**
     * 0 未锁定 1 锁定
     */
    private Integer lock = 0;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;



    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
