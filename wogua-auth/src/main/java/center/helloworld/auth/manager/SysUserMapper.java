package center.helloworld.auth.manager;

import center.helloworld.common.core.entity.system.SystemUser;
import center.helloworld.common.entity.system.SysUser;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@DS("system")
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 分页
     *
     * @param pageInfo
     * @param searchKey
     * @return
     */
    IPage<SysUser> page(Page<SysUser> pageInfo, @Param("searchKey") String searchKey);

    /**
     * 根据手机号查询
     * @param mobile
     * @return
     */
    SysUser findByMobile(@Param("mobile") String mobile);
}
