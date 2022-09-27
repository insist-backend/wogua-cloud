package center.helloworld.common.entity.blog;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 16:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogUpload extends Model<BlogUpload> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String uuid;

    /**
     * 名称
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;


    @Override
    public Serializable pkVal() {
        return this.uuid;
    }
}
