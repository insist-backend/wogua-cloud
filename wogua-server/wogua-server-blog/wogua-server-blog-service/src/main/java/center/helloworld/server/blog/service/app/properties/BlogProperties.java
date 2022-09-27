package center.helloworld.server.blog.service.app.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/5/9 15:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "blog")
public class BlogProperties {

    /**
     * blog上传图片路径
     */
    private String imgPath;
}
