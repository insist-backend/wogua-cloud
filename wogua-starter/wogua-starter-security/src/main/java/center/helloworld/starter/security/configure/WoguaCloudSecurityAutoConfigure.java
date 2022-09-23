package center.helloworld.starter.security.configure;

import center.helloworld.common.core.constant.WoguaConstant;
import center.helloworld.starter.security.handler.WoguaAccessDeniedHandler;
import center.helloworld.starter.security.handler.WoguaAuthExceptionEntryPoint;
import center.helloworld.starter.security.properties.WoguaCloudSecurityProperties;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Base64Utils;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(WoguaCloudSecurityProperties.class)
@ConditionalOnProperty(value = "wogua.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class WoguaCloudSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {

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

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WoguaCloudSecurityInteceptorConfigure woguaCloudSecurityInteceptorConfigure() {
        return new WoguaCloudSecurityInteceptorConfigure();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(DefaultTokenServices.class)
    public WoguaUserInfoTokenServices woguaUserInfoTokenServices(ResourceServerProperties properties) {
        return new WoguaUserInfoTokenServices(properties.getUserInfoUri(), properties.getClientId());
    }

    /**
     * feign远程调用拦截器，请求头携带认证信息
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(WoguaConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(WoguaConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, WoguaConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }

    /**
     * 获取当前令牌内容
     *
     * @return String 令牌内容
     */
    public String getCurrentTokenValue() {
        try {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) getOauth2Authentication().getDetails();
            return details.getTokenValue();
        } catch (Exception ignore) {
            return null;
        }
    }

    private OAuth2Authentication getOauth2Authentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (OAuth2Authentication) authentication;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
