package com.shanejim.myweb.personaladmin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-30 16:44
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Autowired
    MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    SelfAuthenticationProvider provider; // 自定义安全认证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .httpBasic()
//                .authenticationEntryPoint(myAuthenticationEntryPoint)
//                .and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/", "/app/**", "/css/**", "/img/**", "/server/**", "/vendor/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs" ,
                        "/pay/**","/page/**","/employees/**").permitAll()
//                .antMatchers("/payRecord/**").hasAuthority("admin21")
                .anyRequest().authenticated()               // 任何请求,登录后可以访问
                .and()
                .formLogin()
                //.loginPage("/authentication/require") //定义当需要用户登录时候，转到的登录页面。前后端分离时不配置
                //.loginProcessingUrl("/usr/login")     //自定义登录接口的地址，不配置的话默认是/login，只能form表单提交，不能传json
                .usernameParameter("userName")     //自定义登录参数，默认是username
                .passwordParameter("passWord")      //自定义登录参数，默认是password

                .successHandler(myAuthenctiationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
                .and()
                .csrf().disable();

        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);

//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
//                .csrf().disable();
    }
}
