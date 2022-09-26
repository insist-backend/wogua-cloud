package center.helloworld.common.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

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

    /**
     * 角色ID
     */
    @TableField(exist = false)
    private String roleIds;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String roleNames;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
