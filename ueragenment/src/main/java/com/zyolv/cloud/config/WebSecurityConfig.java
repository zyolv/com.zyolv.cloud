package com.zyolv.cloud.config;


import com.zyolv.cloud.handler.FailureHandler;
import com.zyolv.cloud.handler.LogoutHandler;
import com.zyolv.cloud.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private FailureHandler failureHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .formLogin()

                .loginProcessingUrl("/oauth/taken").permitAll()
                .successHandler(successHandler).permitAll()
                .failureHandler(failureHandler).permitAll().and()
                .logout().logoutSuccessHandler(logoutHandler).and()
                // 不通过Session获取SecurityContext
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeRequests()
                //  允许匿名访问
                .antMatchers("/redis/**","/oauth/**", "/actuator/**","/user/login").anonymous()
                .anyRequest().authenticated();

        //.antMatchers("/**").anonymous();


    }
}
