package center.helloworld.server.system.app.service;

import center.helloworld.common.entity.system.SysPermission;
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
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(Integer userId);

    /**
     * 排序列表
     * @return
     */
    List<SysPermission> orderList();

    /**
     * 根据角色ID查询权限信息
     * @param roleId
     * @return
     */
    List<SysPermission> findByRole(Integer roleId);

    /**
     * 根据Code查询
     * @param code
     * @return
     */
    SysPermission findByCode(String code);

    /**
     * 根据PID删除
     * @param pid
     * @return
     */
    List<Integer> deleteByPid(Integer pid);

    /**
     * 查询父级菜单集合
     * @param id
     * @return
     */
    List<Integer> findPids(Integer id);

    /**
     *  根据code查询 忽略 permissionId
     * @param code
     * @param permissionId
     * @return
     */
    SysPermission findByCodeIgnorePermission(String code, Integer permissionId);
}
