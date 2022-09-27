package center.helloworld.server.blog.service.app.service;

import center.helloworld.common.entity.blog.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:09
 */
public interface BlogTagService extends IService<BlogTag> {
    /**
     * 列表
     * @param searchKey
     * @return
     */
    List<BlogTag> list(String searchKey);
}
