package center.helloworld.server.system.service.app.web;

import center.helloworld.common.core.base.PageResult;
import center.helloworld.common.core.base.Result;
import center.helloworld.common.core.code.ResCode;
import center.helloworld.common.core.exception.ApiException;
import center.helloworld.common.core.utils.BCyptUtil;
import center.helloworld.common.entity.system.SysUser;
import center.helloworld.server.system.service.app.service.SysUserRoleService;
import center.helloworld.server.system.service.app.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;

    private static final String INIT_PWD = "wogua";

    /**
     * 分页
     * @param searchKey
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("page/{pageNumber}/{pageSize}")
    public Result page(String searchKey,
                       @PathVariable("pageNumber") Integer pageNumber,
                       @PathVariable("pageSize") Integer pageSize) {
        PageResult pr = userService.page(pageNumber, pageSize, searchKey);
        return Result.ok(pr);
    }

    /**
     * 添加
     * @param sysUser
     * @return
     */
    @PostMapping
    @Transactional
    public Result add(@RequestBody SysUser sysUser) {
        // 手机号判断
        boolean existMobile = checkMobile(sysUser.getMobile());
        if(existMobile) throw new ApiException(ResCode.ERROR_MOBILE_EXIST);
        // 邮箱判断
        boolean existMail = checkMail(sysUser.getMail());
        if(existMail) throw new ApiException(ResCode.ERROR_EMIAL_EXIST);
        // 添加用户
        String[] passwordAndSalt = BCyptUtil.getPasswordAndSalt(INIT_PWD);
        sysUser.setPassword(passwordAndSalt[0]);
        userService.save(sysUser);
        // 添加用户角色
        if(StringUtils.isNotBlank(sysUser.getRoleIds())) {
            String[] roleIdsStr = sysUser.getRoleIds().split(",");
            userRoleService.add(sysUser.getId(),roleIdsStr);
        }
        return Result.ok();
    }

    /**
     * 修改用户
     * @param sysUser
     * @param id
     * @return
     */
    @Transactional
    @PutMapping("{id}")
    public Result update(@RequestBody SysUser sysUser,@PathVariable("id") Integer id) {
        // 校验手机号
        boolean existMobile = checkMobile(sysUser.getMobile(),id);
        if(existMobile) throw new ApiException(ResCode.ERROR_MOBILE_EXIST);
        // 校验邮箱
        boolean existMail = checkMail(sysUser.getMail(),id);
        if(existMail) throw new ApiException(ResCode.ERROR_EMIAL_EXIST);
        // 更新用户
        userService.updateById(sysUser);
        // 更新角色
        if(StringUtils.isNotBlank(sysUser.getRoleIds())) {
            String[] roleIdsStr = sysUser.getRoleIds().split(",");
            userRoleService.update(id,roleIdsStr);
        }
        return Result.ok();
    }

    /**
     * 校验邮箱
     * @param mail
     * @param userId
     * @return
     */
    private boolean checkMail(String mail, Integer userId) {
        // TODO 格式
        SysUser user = userService.findByMailIngoreUserId(mail, userId);
        return user != null;
    }

    /**
     * 校验邮箱
     * @param mobile
     * @param userId
     * @return
     */
    private boolean checkMobile(String mobile, Integer userId) {
        // TODO 格式
        SysUser user = userService.findByMobileIngoreUserId(mobile, userId);
        return user != null;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @Transactional
    public Result del(@PathVariable("id") Integer id) {
        // 删除用户
        userService.removeById(id);
        // 删除用户角色关系
        userRoleService.removeByUserId(id);
        return Result.ok();
    }

    /**
     * 校验手机
     * @param mobile
     * @return
     */
    private boolean checkMobile(String mobile) {
        // TODO 格式
        SysUser user = userService.findByMobile(mobile);
        return user != null;
    }

    /**
     * 校验邮箱
     * @param mail
     * @return
     */
    private boolean checkMail(String mail) {
        // TODO 格式
        SysUser user = userService.findByMail(mail);
        return user != null;
    }
}
