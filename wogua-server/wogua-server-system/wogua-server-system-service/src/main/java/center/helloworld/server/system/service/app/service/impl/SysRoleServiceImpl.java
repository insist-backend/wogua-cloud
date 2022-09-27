package center.helloworld.server.system.service.app.service.impl;

import center.helloworld.common.entity.system.SysRole;
import center.helloworld.server.system.service.app.mapper.SysRoleMapper;
import center.helloworld.server.system.service.app.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public SysRole findByName(String name) {
        return roleMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getName,name));
    }

    /**
     * 根据名称查询并不包含此角色
     * @param name
     * @return
     */
    @Override
    public SysRole findByNameIgnoreRoleId(String name, Integer roleId) {
        return roleMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getName,name).notIn(SysRole::getId,roleId));
    }

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @Override
    public List<SysRole> list(String searchKey) {
        LambdaQueryWrapper<SysRole> qw = new LambdaQueryWrapper<SysRole>();
        if(StringUtils.isNotBlank(searchKey)) qw.like(SysRole::getName, searchKey);
        qw.orderByDesc(SysRole::getCreatetime);
        return roleMapper.selectList(qw);
    }
}
