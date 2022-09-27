package center.helloworld.server.blog.service.app.service.impl;

import center.helloworld.common.core.base.PageResult;
import center.helloworld.common.entity.blog.BlogArticle;
import center.helloworld.common.vo.blog.BlogArticleSearchVO;
import center.helloworld.server.blog.service.app.mapper.BlogArticleMapper;
import center.helloworld.server.blog.service.app.service.BlogArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:10
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    private BlogArticleMapper articleMapper;

    /**
     * 分页
     * @param pageNumber
     * @param pageSize
     * @param searchVO
     * @return
     */
    @Override
    public PageResult page(Integer pageNumber, Integer pageSize, BlogArticleSearchVO searchVO) {
        Page<BlogArticle> pageInfo = new Page<>(pageNumber, pageSize);
        IPage<BlogArticle> pr = articleMapper.page(pageInfo,searchVO);
        return new PageResult(pr.getTotal(), pr.getRecords());
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public BlogArticle findById(Integer id) {
        return articleMapper.findById(id);
    }
}

