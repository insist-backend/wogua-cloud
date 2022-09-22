package center.helloworld.server.demo.service.annotation;

import center.helloworld.server.demo.service.config.WoguaAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/22 11:40
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WoguaAuthExceptionConfigure.class)
public @interface EnableWoguaAuthExceptionHandler {
}
