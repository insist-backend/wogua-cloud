package center.helloworld.common.vo.blog;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/10 11:08
 */
@Data
public class BlogArticleSearchVO {

    /**
     * 关键字
     */
    private String searchKey;

    /**
     * 类别
     */
    private List<Integer> categories;

    /**
     * 标签
     */
    private List<Integer> tags;
}
