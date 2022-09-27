package center.helloworld.server.blog.service.app.service.impl;

import center.helloworld.common.entity.blog.BlogTag;
import center.helloworld.server.blog.service.app.mapper.BlogTagMapper;
import center.helloworld.server.blog.service.app.service.BlogTagService;
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
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogTagMapper tagMapper;

    /**
     * 列表
     * @param searchKey
     * @return
     */
    @Override
    public List<BlogTag> list(String searchKey) {
        LambdaQueryWrapper<BlogTag> qw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(searchKey)) qw.like(BlogTag::getName,searchKey);
        qw.orderByDesc(BlogTag::getId);
        return tagMapper.selectList(qw);
    }
}
