package center.helloworld.server.demo.service.config;

import center.helloworld.server.demo.service.handler.WoguaAccessDeniedHandler;
import center.helloworld.server.demo.service.handler.WoguaAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/22 11:38
 */
public class WoguaAuthExceptionConfigure {
    @PostConstruct
    public void test() {
        System.out.println();
    }

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public WoguaAccessDeniedHandler accessDeniedHandler() {
        return new WoguaAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public WoguaAuthExceptionEntryPoint authenticationEntryPoint() {
        return new WoguaAuthExceptionEntryPoint();
    }
}
