package com.example.security;

import com.example.model.RoleList;
import com.example.service.interfaces.UserService;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserService userService;
    private final UserPrincipalDetailsService userPrincipalDetailsService;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;

    public SecurityConfiguration(UserService userService, UserPrincipalDetailsService userPrincipalDetailsService,
                                 MessageSource messageSource, CustomValidator customValidator) {
        this.userService = userService;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),this.userService, messageSource, customValidator))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),this.userService))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/users/register").permitAll()
                .antMatchers("/logout").authenticated()
                .antMatchers("/games/*").permitAll()
                .antMatchers("/statistics/*").permitAll()
                .antMatchers("/game-access/*").authenticated()
                .antMatchers("/game-room/*").permitAll()
                .antMatchers(HttpMethod.GET,"/answer/*").permitAll()
                .antMatchers(HttpMethod.DELETE,"/answer/*").authenticated()
                .antMatchers(HttpMethod.PUT,"/answer/*").authenticated()
                .antMatchers(HttpMethod.POST,"/answer/*").authenticated()
                .antMatchers(HttpMethod.GET,"/question/*").permitAll()
                .antMatchers(HttpMethod.DELETE,"/question/delete/*").authenticated()
                .antMatchers(HttpMethod.PUT,"/question/update/*").authenticated()
                .antMatchers(HttpMethod.POST,"/question/save").authenticated()
                .antMatchers("/player/*").permitAll()
                .antMatchers("/level/*").authenticated()
                .antMatchers("/category/*").authenticated()
                .antMatchers("/photo/*").authenticated()
                .antMatchers(HttpMethod.GET,"/users/checkAllUsers").hasRole(RoleList.ADMIN)
                .antMatchers(HttpMethod.GET,"/users/findAllUsers").permitAll()
                .antMatchers(HttpMethod.GET,"/users//pageable").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/delete/*").hasRole(RoleList.ADMIN)
                .antMatchers(HttpMethod.PUT,"/users/update/*").authenticated()
                .antMatchers(HttpMethod.POST,"/users/block/*").hasRole(RoleList.ADMIN)
                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userPrincipalDetailsService);
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
