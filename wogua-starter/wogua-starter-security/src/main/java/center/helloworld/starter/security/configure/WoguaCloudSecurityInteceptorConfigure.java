package center.helloworld.starter.security.configure;

import center.helloworld.starter.security.interceptor.WoguaServerProtectInterceptor;
import center.helloworld.starter.security.properties.WoguaCloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WoguaCloudSecurityInteceptorConfigure implements WebMvcConfigurer {

    private WoguaCloudSecurityProperties properties;

    @Autowired
    public void setProperties(WoguaCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor febsServerProtectInterceptor() {
        WoguaServerProtectInterceptor febsServerProtectInterceptor = new WoguaServerProtectInterceptor();
        febsServerProtectInterceptor.setProperties(properties);
        return febsServerProtectInterceptor;
    }

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(febsServerProtectInterceptor());
    }
}
