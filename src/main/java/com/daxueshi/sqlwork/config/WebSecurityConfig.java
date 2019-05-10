package com.daxueshi.sqlwork.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * @author onion
 * @date 2019-04-21 -08:36
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //权限分两部分，一部分为拦截的路径，一部分为访问路径需要的权限
        //antMatchers表示拦截路径
        //anyRequest任何的请求认证后才能访问
        //使csrf拦截失效
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/stu").hasRole("Student")
                .antMatchers("/gra").hasRole("Graduate");
        http.formLogin().usernameParameter("email").passwordParameter("password")
        .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication();
        //auth.inMemoryAuthentication().withUser("user").password("123456").authorities("Student");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password from users where email=?")
                .authoritiesByUsernameQuery("select email from users")
                .passwordEncoder(bCryptPasswordEncoder);
    }


}
