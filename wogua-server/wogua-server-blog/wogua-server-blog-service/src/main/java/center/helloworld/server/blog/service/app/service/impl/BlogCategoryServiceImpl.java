package center.helloworld.server.blog.service.app.service.impl;

import center.helloworld.common.entity.blog.BlogCategory;
import center.helloworld.server.blog.service.app.mapper.BlogCategoryMapper;
import center.helloworld.server.blog.service.app.service.BlogCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Autowired
    private BlogCategoryMapper categoryMapper;
    /**
     * 列表
     * @param searchKey
     * @return
     */
    @Override
    public List<BlogCategory> list(String searchKey) {
        LambdaQueryWrapper<BlogCategory> qw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(searchKey)) qw.like(BlogCategory::getName,searchKey);
        qw.orderByDesc(BlogCategory::getId);
        return categoryMapper.selectList(qw);
    }
}
