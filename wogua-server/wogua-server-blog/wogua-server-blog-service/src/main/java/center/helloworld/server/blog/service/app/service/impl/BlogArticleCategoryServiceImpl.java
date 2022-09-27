package center.helloworld.server.blog.service.app.service.impl;

import center.helloworld.common.entity.blog.BlogArticleCategory;
import center.helloworld.server.blog.service.app.mapper.BlogArticleCategoryMapper;
import center.helloworld.server.blog.service.app.service.BlogArticleCategoryService;
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
public class BlogArticleCategoryServiceImpl extends ServiceImpl<BlogArticleCategoryMapper, BlogArticleCategory> implements BlogArticleCategoryService {

    @Autowired
    private BlogArticleCategoryMapper blogArticleCategoryMapper;

    /**
     * 根据类别ID删除
     * @param categoryId
     */
    @Override
    public void deleteByCategoryId(Integer categoryId) {
        blogArticleCategoryMapper.delete(new LambdaQueryWrapper<BlogArticleCategory>().eq(BlogArticleCategory::getCategoryId,categoryId));
    }

    /**
     * 根据文章ID删除
     * @param articleId
     */
    @Override
    public void deleteByArticleId(Integer articleId) {
        blogArticleCategoryMapper.delete(new LambdaQueryWrapper<BlogArticleCategory>().eq(BlogArticleCategory::getArticleId,articleId));
    }

    /**
     * 添加
     * @param articleId
     * @param categories
     */
    @Override
    public void save(Integer articleId, List<Integer> categories) {
        if(articleId == null || CollectionUtils.isEmpty(categories)) return;
        BlogArticleCategory articleCategory = new BlogArticleCategory();
        articleCategory.setArticleId(articleId);
        for (Integer category : categories) {
            articleCategory.setCategoryId(category);
            blogArticleCategoryMapper.insert(articleCategory);
        }
    }
}
