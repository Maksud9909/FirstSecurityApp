package com.maksudrustamov.springboot.firstsecurityapp.config;

import com.maksudrustamov.springboot.firstsecurityapp.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Этот класс является главным где мы настраиваем SpringSecurity
 * Здесь мы будем настраивать аутентификацию и авторизацию, и все другое
 */
//@Configuration
@EnableWebSecurity // это дает понять Spring, что это конфигурационный файл для Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    PersonDetailsService personDetailsService;
    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    /**
     * Конфигирируем Spring Security
     * Конфигурируем Авторизацию то есть саму страницу для аутентификации
     * @param httpSecurity в этот метод поступает http запрос
     */
    protected void configure(HttpSecurity httpSecurity) throws Exception{


        // тут мы пишем условия
        httpSecurity.csrf().disable() // отключаем защиту от межсайтовой поделки
                .authorizeRequests()
                .antMatchers("/auth/login","/error").permitAll() // если ноунейм юзер заходит мы его пускаем
                .anyRequest().authenticated()// для всех остальных запросов пользователь должен быть аутентифицированным
                .and()
                .formLogin().loginPage("/auth/login") // тут мы пишем где кастомная страница нужна для аутентификации
                .loginProcessingUrl("/process_login") // куда мы хотим отправлять данные с формы (/process_login)
                .defaultSuccessUrl("/hello",true)// куда мы попадем в случае успешной аутентификации, второй, чтобы по любому нас туда отправлял в случае успеха
                .failureUrl("/auth/login?error"); // мы здесь говорим, что если не получится, то нужно идти в страницу error
    }


    // настраивает аутентификацию
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder()); // мы дали понять спрингу, что надо именно это service использовать
    }


    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance(); // мы сказали, что пароль не шифруется
    }



}
