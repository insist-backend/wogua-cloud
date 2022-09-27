package center.helloworld.server.system.service.app.service.impl;

import center.helloworld.common.entity.system.SysPermission;
import center.helloworld.server.system.service.app.mapper.SysPermissionMapper;
import center.helloworld.server.system.service.app.service.SysPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Slf4j
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 根据用户ID查询
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> findByUserId(Integer userId) {
        return permissionMapper.findByUserId(userId);
    }

    /**
     * 根据角色ID查询权限信息
     * @param roleId
     * @return
     */
    @Override
    public List<SysPermission> findByRole(Integer roleId) {
        return permissionMapper.findByRole(roleId);
    }

    /**
     *  根据code查询 忽略 permissionId
     * @param code
     * @param permissionId
     * @return
     */
    @Override
    public SysPermission findByCodeIgnorePermission(String code, Integer permissionId) {
        return permissionMapper.selectOne(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getCode,code).notIn(SysPermission::getId,permissionId));
    }

    /**
     * 查询父级菜单集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findPids(Integer id) {
        LinkedList<Integer> list = new LinkedList<>();
        SysPermission permission = permissionMapper.selectById(id);
        while (permission != null && permission.getPid() != 0) {
            permission = permissionMapper.selectById(permission.getPid());
            list.addFirst(permission.getId());
        }
        return list;
    }

    /**
     * 根据PID删除
     * @param pid
     * @return
     */
    @Override
    public List<Integer> deleteByPid(Integer pid) {
        List<Integer> collectPerIds = new ArrayList<>();
        List<SysPermission> permissions = findByPid(pid);
        if(CollectionUtils.isEmpty(permissions)) return collectPerIds;
        doCollect(permissions, collectPerIds);
        return collectPerIds;
    }

    /**
     * 采集
     * @param permissions
     * @param collectPerIds
     */
    private void doCollect(List<SysPermission> permissions, List<Integer> collectPerIds) {
        if(CollectionUtils.isEmpty(permissions)) return;
        for (SysPermission permission : permissions) {
            collectPerIds.add(permission.getId());
            permissionMapper.deleteById(permission.getId());
            List<SysPermission> childPermissions = findByPid(permission.getId());
            doCollect(childPermissions,collectPerIds);
        }
    }

    /**
     * 根据PID 查询
     * @param pid
     * @return
     */
    public List<SysPermission> findByPid(Integer pid) {
        return permissionMapper.selectList(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getPid,pid));
    }

    /**
     * 根据Code查询
     * @param code
     * @return
     */
    @Override
    public SysPermission findByCode(String code) {
        return permissionMapper.selectOne(new LambdaQueryWrapper<SysPermission>().eq(SysPermission::getCode,code));
    }

    /**
     * 排序列表
     * @return
     */
    @Override
    public List<SysPermission> orderList() {
        return permissionMapper.orderList();
    }
}
