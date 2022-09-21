package center.helloworld.auth.config;

import center.helloworld.auth.service.WoguaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author zhishun.cai
 * @since 2022/9/21 16:16
 */
@Order(2)
@EnableWebSecurity
public class WoguaSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private WoguaUserDetailService userDetailService;

   @Autowired
   private PasswordEncoder passwordEncoder;

    /**
     * 认证管理器
     * Tip: 因为密码模式需要使用到这个Bean
     * 当然，你可以自己实现PasswordEncoder接口，这里为了方便就直接使用BCryptPasswordEncoder了
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 安全配置类只对/oauth/开头的请求有效。
        http.requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
