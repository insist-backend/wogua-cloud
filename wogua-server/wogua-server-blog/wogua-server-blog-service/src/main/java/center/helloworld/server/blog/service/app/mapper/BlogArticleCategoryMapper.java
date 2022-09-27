package center.helloworld.server.blog.service.app.mapper;

import center.helloworld.common.entity.blog.BlogArticleCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhishun.cai
 * @since 2021-12-02
 */
@Mapper
public interface BlogArticleCategoryMapper extends BaseMapper<BlogArticleCategory> {
}
