package center.helloworld.server.blog.service.app.service.impl;

import center.helloworld.common.entity.blog.BlogArticleTag;
import center.helloworld.server.blog.service.app.mapper.BlogArticleTagMapper;
import center.helloworld.server.blog.service.app.service.BlogArticleTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:10
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements BlogArticleTagService {

    @Autowired
    private BlogArticleTagMapper articleTagMapper;

    /**
     * 根据标签删除
     * @param tagId
     */
    @Override
    public void deleteByTagId(Integer tagId) {
        articleTagMapper.delete(new LambdaQueryWrapper<BlogArticleTag>().eq(BlogArticleTag::getTagId,tagId));
    }

    /**
     * 根据文章ID删除
     * @param articleId
     */
    @Override
    public void deleteByArticleId(Integer articleId) {
        articleTagMapper.delete(new LambdaQueryWrapper<BlogArticleTag>().eq(BlogArticleTag::getArticleId,articleId));
    }

    /**
     * 添加
     * @param articleId
     * @param tags
     */
    @Override
    public void save(Integer articleId, List<Integer> tags) {
        if(articleId == null || CollectionUtils.isEmpty(tags)) return;
        BlogArticleTag articleTag = new BlogArticleTag();
        articleTag.setArticleId(articleId);
        for (Integer tag : tags) {
            articleTag.setTagId(tag);
            articleTagMapper.insert(articleTag);
        }
    }
}
