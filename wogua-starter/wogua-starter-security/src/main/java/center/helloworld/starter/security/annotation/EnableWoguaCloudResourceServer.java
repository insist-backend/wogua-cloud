package center.helloworld.starter.security.annotation;

import center.helloworld.starter.security.configure.WoguaCloudResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WoguaCloudResourceServerConfigure.class)
public @interface EnableWoguaCloudResourceServer {
}
