package center.helloworld.server.system.app.service;

import center.helloworld.common.entity.system.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 添加
     * @param userId
     * @param roleIdsStr
     */
    void add(Integer userId, String[] roleIdsStr);

    /**
     * 根据用户ID删除
     * @param userId
     */
    void removeByUserId(Integer userId);

    /**
     * 更新用户角色关系
     * @param userId
     * @param roleIdsStr
     */
    void update(Integer userId, String[] roleIdsStr);

    /**
     * 根据角色ID删除
     * @param roleId
     */
    void removeByRoleId(Integer roleId);
}
