package com.maksudrustamov.springboot.firstsecurityapp.config;

import com.maksudrustamov.springboot.firstsecurityapp.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



/**
 * Этот класс является главным где мы настраиваем SpringSecurity
 * Здесь мы будем настраивать аутентификацию и авторизацию, и все другое
 */
@Configuration
@EnableWebSecurity // это дает понять Spring, что это конфигурационный файл для Security
public class SecurityConfig  {

    private final AuthProviderImpl authProvider;
    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    // настраивает аутентификацию
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider); // мы дали понять спрингу, что надо именно это authProvider использовать
    }
}
