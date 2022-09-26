package center.helloworld.server.system.app.mapper;

import center.helloworld.common.entity.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
