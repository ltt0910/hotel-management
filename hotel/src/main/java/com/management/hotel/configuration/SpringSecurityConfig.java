package com.management.hotel.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsImpl userDetails;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests()
                .antMatchers("/home/login", "/logout", "/assets/**","/home/forgot_password","/home/signup","/api/transaction/**").permitAll()    // ai cung co quyen login va logout
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/login_check")    //action login
                .loginPage("/home/login")
                .defaultSuccessUrl("/room/lists")
                .failureUrl("/home/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
//                .failureHandler("")
                .and().exceptionHandling().accessDeniedPage("/home/deny")
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
}
