package center.helloworld.auth.configure;

import center.helloworld.auth.filter.ValidateCodeFilter;
import center.helloworld.auth.handler.WoguaWebLoginFailureHandler;
import center.helloworld.auth.handler.WoguaWebLoginSuccessHandler;
import center.helloworld.auth.service.impl.WoguaUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
@RequiredArgsConstructor
public class WoguaSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final WoguaUserDetailService userDetailService;

   private final PasswordEncoder passwordEncoder;

    private final ValidateCodeFilter validateCodeFilter;

    private final WoguaWebLoginSuccessHandler successHandler;

    private final WoguaWebLoginFailureHandler failureHandler;

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
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers("/oauth/**","/login")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and().csrf().disable()
                .httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
