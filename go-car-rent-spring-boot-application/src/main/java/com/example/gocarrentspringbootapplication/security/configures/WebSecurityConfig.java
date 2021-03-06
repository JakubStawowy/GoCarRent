package com.example.gocarrentspringbootapplication.security.configures;

import com.example.gocarrentspringbootapplication.security.enums.UserRoles;
import com.example.gocarrentspringbootapplication.security.filters.JsonWebTokenFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
                .csrf().disable()
                .addFilterBefore(
                        new JsonWebTokenFilter(authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class
                )
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/register").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger*").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/announcements/{id}/block").hasRole(UserRoles.ROLE_ADMIN.getValueWithoutPrefix())
                .antMatchers(HttpMethod.PUT, "/api/announcements/{id}/unlock").hasRole(UserRoles.ROLE_ADMIN.getValueWithoutPrefix())
                .antMatchers(HttpMethod.GET, "/api/announcements/blocked").hasRole(UserRoles.ROLE_ADMIN.getValueWithoutPrefix())
                .anyRequest()
                .authenticated();
    }
}
