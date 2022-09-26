package center.helloworld.auth.manager;

import center.helloworld.common.entity.system.SysPermission;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@DS("system")
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    List<SysPermission> findByUserId(@Param("userId") Integer userId);

    /**
     * 排序列表
     *
     * @return
     */
    List<SysPermission> orderList();

    /**
     * 根据角色ID查询权限信息
     *
     * @param roleId
     * @return
     */
    List<SysPermission> findByRole(@Param("roleId") Integer roleId);

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    String findUserPermissions(Integer userId);
}
