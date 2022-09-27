package center.helloworld.server.system.service.app.web;

import center.helloworld.common.core.base.Result;
import center.helloworld.common.core.code.ResCode;
import center.helloworld.common.core.exception.ApiException;
import center.helloworld.common.entity.system.SysPermission;
import center.helloworld.server.system.service.app.service.SysPermissionService;
import center.helloworld.server.system.service.app.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysRolePermissionService rolePermissionService;

    /**
     * 添加
     * @param sysPermission
     * @return
     */
    @PostMapping
    public Result add(@RequestBody SysPermission sysPermission) {
        // 判断权限标识是否存在
        SysPermission permission = permissionService.findByCode(sysPermission.getCode());
        if(permission != null) throw new ApiException(ResCode.ERROR_PERMISSION_EXIST);
        permissionService.save(sysPermission);
        return Result.ok();
    }

    /**
     * 树形结构
     * @return
     */
    @GetMapping("tree")
    public Result tree() {
        List<SysPermission> permissions = permissionService.orderList();
        permissions = toTree(permissions);
        return Result.ok(permissions);
    }

    /**
     * 根据角色ID查询权限信息
     * @param roleId
     * @return
     */
    @GetMapping("findByRole/{roleId}")
    public Result findByRole(@PathVariable("roleId") Integer roleId) {
        List<SysPermission> permissions = permissionService.findByRole(roleId);
        return Result.ok(permissions);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @Transactional
    public Result del(@PathVariable("id") Integer id) {
        // 删除权限
        permissionService.removeById(id);
        // 删除子级、孙级权限
        List<Integer> delPerIds = permissionService.deleteByPid(id);
        // 删除角色权限关系
        delPerIds.add(id);
        rolePermissionService.removeByPermissionIds(delPerIds);
        return Result.ok();
    }

    /**
     * 查询父级菜单集合
     * @param id
     * @return
     */
    @GetMapping("findPids/{id}")
    public Result findPids(@PathVariable("id") Integer id) {
        List<Integer> pids = permissionService.findPids(id);
        return Result.ok(pids);
    }

    /**
     * 更新
     * @param id
     * @param sysPermission
     * @return
     */
    @PutMapping("{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody SysPermission sysPermission) {
        // 判断权限标识是否存在
        SysPermission permission = permissionService.findByCodeIgnorePermission(sysPermission.getCode(), id);
        if(permission != null) throw new ApiException(ResCode.ERROR_PERMISSION_EXIST);
        sysPermission.setId(id);
        permissionService.updateById(sysPermission);
        return Result.ok();
    }

    /**
     * 生成树形结构
     * @param permissions
     * @return
     */
    private List<SysPermission> toTree(List<SysPermission> permissions) {
        List<SysPermission> resPermissions = new ArrayList<>();
        for (SysPermission mainPer : permissions) {
            if(mainPer.getPid() == 0) resPermissions.add(mainPer);
            Integer mainId = mainPer.getId();
            for (SysPermission subPer : permissions) {
                if(mainId.equals(subPer.getId())) continue;
                if(mainId.equals(subPer.getPid())) {
                    List<SysPermission> children = mainPer.getChildren();
                    if(children == null) {
                        children = new ArrayList<>();
                        mainPer.setChildren(children);
                    }
                    children.add(subPer);
                }
            }
        }
        return resPermissions;
    }

}
