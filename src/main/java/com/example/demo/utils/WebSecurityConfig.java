package com.example.demo.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Descpription: Describe the function of class
 * @Author: Created by xucheng.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity httpSecurity) throws Exception {
    //    authorizeRequests()指定哪些URL需要被保护哪些不需要 e.g. /和/home 不需要任何认证就可以访问 其他页面必须通过验证
    //    formLogin() 指定用户需要进行登录验证的时候转到的用户登录界面
    //    Spring Security提供了一个过滤器来拦截请求并验证用户身份。如果用户身份认证失败，页面就重定向到/login?error，并且页面中会展现相应的错误信息。
    //    若用户想要注销登录，可以通过访问/login?logout请求，在完成注销之后，页面展现相应的成功消息。
    //    启用应用，并访问http://localhost:8080/，可以正常访问。
    //    但是访问http://localhost:8080/hello的时候被重定向到了http://localhost:8080/login页面，因为没有登录，用户没有访问权限，通过输入用户名user和密码password进行登录后，跳转到了Hello World页面，
    //    再通过访问http://localhost:8080/login?logout，就可以完成注销操作。
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/","/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();

    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//  内存新建用户，指定用户名、密码、角色
        auth.inMemoryAuthentication()
                .withUser("xc").password("xc").roles("ADMIN");
        auth.inMemoryAuthentication()
                    .withUser("demouser").password("demouser").roles("USER");
    }

}
