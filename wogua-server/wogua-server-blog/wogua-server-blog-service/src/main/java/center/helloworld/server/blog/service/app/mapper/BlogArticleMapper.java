package center.helloworld.server.blog.service.app.mapper;

import center.helloworld.common.entity.blog.BlogArticle;
import center.helloworld.common.vo.blog.BlogArticleSearchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Mapper
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {
    /**
     * 分页
     *
     * @param pageInfo
     * @param searchVO
     * @return
     */
    IPage<BlogArticle> page(Page<BlogArticle> pageInfo, @Param("searchVO") BlogArticleSearchVO searchVO);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    BlogArticle findById(@Param("id") Integer id);
}
