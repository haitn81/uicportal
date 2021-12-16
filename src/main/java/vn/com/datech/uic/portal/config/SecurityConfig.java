/*
 * Copyright (C) DATECH, Inc - All Rights Reserved
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  Written by Nguyen Trung Hieu, 2021
 */

package vn.com.datech.uic.portal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import vn.com.datech.uic.portal.service.SecurityService;

/**
 * Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * JwtAuthenticationEntryPoint.
     */
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    /**
     * SecurityService.
     */
    private SecurityService securityService;

    /**
     * Default constructor.
     *
     * @param pJwtAuthenticationEntryPoint JwtAuthenticationEntryPoint
     * @param pSecurityService             SecurityService
     */
    public SecurityConfig(
            final JwtAuthenticationEntryPoint pJwtAuthenticationEntryPoint,
            final SecurityService pSecurityService) {
        this.jwtAuthenticationEntryPoint = pJwtAuthenticationEntryPoint;
        this.securityService = pSecurityService;
    }

    /**
     * Config global.
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception when error
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth)
            throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(securityService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Password Encoder bean.
     *
     * @return BCryptPasswordEncoder password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * AuthenticationManager.
     * @return AuthenticationManager
     * @throws Exception when error
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * DaoAuthenticationProvider bean.
     *
     * @return DaoAuthenticationProvider dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(securityService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    /**
     * Configure.
     * @param httpSecurity HttpSecurity
     * @throws Exception when error
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll().and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * configure.
     * @param web WebSecurity
     * @throws Exception when error
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(defaultHttpFirewall());
    }

    /**
     * Default http firewall http firewall.
     *
     * @return the http firewall
     */
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
