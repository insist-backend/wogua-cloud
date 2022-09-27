package center.helloworld.common.entity.blog;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BlogArticle {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 内容
     */
    private String content;

    /**
     * 分类
     */
    @TableField(exist = false)
    private List<Integer> categories;

    /**
     * 分类ID , 隔开
     */
    @TableField(exist = false)
    private String categoryIds;

    /**
     * 分类名称
     */
    @TableField(exist = false)
    private String categoryNames;

    /**
     * 标签
     */
    @TableField(exist = false)
    private List<Integer> tags;

    /**
     * 标签ID , 隔开
     */
    @TableField(exist = false)
    private String tagIds;

    /**
     * 标签名称
     */
    @TableField(exist = false)
    private String tagNames;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 修改时间
     */
    private LocalDateTime updatetime;

}
