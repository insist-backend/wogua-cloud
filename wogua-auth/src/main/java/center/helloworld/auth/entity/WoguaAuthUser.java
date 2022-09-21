package center.helloworld.auth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/21 16:34
 */
@Data
public class WoguaAuthUser implements Serializable {
    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
