package center.helloworld.server.system.service.app.service;

import center.helloworld.common.entity.system.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 根据角色ID删除
     * @param roleId
     */
    void removeByRoleId(Integer roleId);

    /**
     * 添加
     * @param roldId
     * @param permissionIds
     */
    void add(Integer roldId, List<Integer> permissionIds);

    /**
     * 更具权限集合删除
     * @param delPerIds
     */
    void removeByPermissionIds(List<Integer> delPerIds);
}
