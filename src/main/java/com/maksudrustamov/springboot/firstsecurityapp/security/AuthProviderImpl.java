package com.maksudrustamov.springboot.firstsecurityapp.security;


import com.maksudrustamov.springboot.firstsecurityapp.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Здесь мы будем проверять пароль введенный с поля, с паролем, который есть в базах данных
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService service;
    @Autowired
    public AuthProviderImpl(PersonDetailsService service) {
        this.service = service;
    }

    /**
     * Здесь будет лежать логика регистрации
     * @param authentication - это логин и пароль
     * @return - возвращает Principal - это непосредственно данные пользователя, это Person Details
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // получили имя пользователя из фронта
        UserDetails personDetails = service.loadUserByUsername(username); // получили пользователя через бд

        String password = authentication.getCredentials().toString(); // пароль из фронта

        if (!password.equals(personDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        // если все успешно, то тогда выбрасываем нового пользователя
        return new UsernamePasswordAuthenticationToken(personDetails,password, Collections.emptyList());
    }




    /**
     * // это более технический метод, если у нас больше чем одна
     * регистраций, то можно использовать
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
