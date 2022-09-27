package center.helloworld.server.blog.service.app.service;

import center.helloworld.common.entity.blog.BlogCategory;
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
public interface BlogCategoryService extends IService<BlogCategory> {

    /**
     * 列表
     * @param searchKey
     * @return
     */
    List<BlogCategory> list(String searchKey);
}
