package com.heq.comp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //用户实现接口
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * http请求的安全处理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //设置不需要拦截过滤的请求
        String[] antPatterns = new String[]{"/login.html","/login","/login*"};
        http.authorizeRequests()//匹配器设置,定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers(antPatterns)
                .permitAll()//提交上面的定义
                .anyRequest()//拦截所有的请求, 任何请求,登录后可以访问
                .authenticated()//定任何经过身份验证的用户都允许使用URL。
                .and()
                .formLogin()//  // 定义当需要用户登录时候，转到的登录页面。
                //.loginPage("/login.html")//自定义的登录页面
                //.loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()//设置登录退出
                .logout().permitAll().invalidateHttpSession(true)//设置session失效
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler);
        //异常处理
       http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
}
