package center.helloworld.server.blog.service.app.service.impl;

import center.helloworld.common.entity.blog.BlogUpload;
import center.helloworld.server.blog.service.app.mapper.BlogUploadMapper;
import center.helloworld.server.blog.service.app.service.BlogUploadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BlogUploadServiceImpl extends ServiceImpl<BlogUploadMapper, BlogUpload> implements BlogUploadService {
}
