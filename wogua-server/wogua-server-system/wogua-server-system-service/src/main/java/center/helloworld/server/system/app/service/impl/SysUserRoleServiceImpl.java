package center.helloworld.server.system.app.service.impl;

import center.helloworld.common.entity.system.SysUserRole;
import center.helloworld.server.system.app.mapper.SysUserRoleMapper;
import center.helloworld.server.system.app.service.SysUserRoleService;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 更新用户角色关系
     * @param userId
     * @param roleIdsStr
     */
    @Override
    public void update(Integer userId, String[] roleIdsStr) {
        removeByUserId(userId);
        add(userId,roleIdsStr);
    }

    /**
     * 根据角色ID删除
     * @param roleId
     */
    @Override
    public void removeByRoleId(Integer roleId) {
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId,roleId));
    }

    /**
     * 根据用户ID删除
     * @param userId
     */
    @Override
    public void removeByUserId(Integer userId) {
        userRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId,userId));
    }

    /**
     * 添加
     * @param userId
     * @param roleIdsStr
     */
    @Override
    public void add(Integer userId, String[] roleIdsStr) {
        if(!ArrayUtil.isNotEmpty(roleIdsStr)) return;
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(userId);
        for (String roleIdStr : roleIdsStr) {
            int roleId = Integer.parseInt(roleIdStr);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }
    }

}
