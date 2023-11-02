package com.javayh.content.hub.configuration;

import com.javayh.content.hub.handler.ContentHubSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author haiji
 */

@Configuration
@EnableWebSecurity
public class ContentHubSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问
        http.authorizeRequests()
                .antMatchers("/external/share/**").permitAll()
                .antMatchers("/share/list/**").permitAll()
                .antMatchers("/external/box").permitAll()
                .antMatchers("/share/verify").permitAll()
                .antMatchers("/images").permitAll()
                .anyRequest().authenticated()
                .and()
                //开启表单登录
                .formLogin()
                //配置登录接口为“/login”
                .loginProcessingUrl("/login")
                .successHandler(new ContentHubSuccessHandler())
                //登录相关的接口不需要认证
                .permitAll()
                .and()
                //关闭 csrf
                .csrf()
                .disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
