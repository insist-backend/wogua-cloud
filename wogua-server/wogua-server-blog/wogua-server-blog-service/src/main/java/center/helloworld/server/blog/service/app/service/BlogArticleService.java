package center.helloworld.server.blog.service.app.service;

import center.helloworld.common.core.base.PageResult;
import center.helloworld.common.entity.blog.BlogArticle;
import center.helloworld.common.vo.blog.BlogArticleSearchVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:09
 */
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 分页
     * @param pageNumber
     * @param pageSize
     * @param searchVO
     * @return
     */
    PageResult page(Integer pageNumber, Integer pageSize, BlogArticleSearchVO searchVO);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    BlogArticle findById(Integer id);
}
