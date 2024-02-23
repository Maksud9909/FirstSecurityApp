package com.maksudrustamov.springboot.firstsecurityapp.security;

import com.maksudrustamov.springboot.firstsecurityapp.entity.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // права доступа, какому то юзеру нельзя будет заходить куда-то
        return null;
    }

    @Override
    public String getPassword() {
        return this.person.getPassword(); // тут мы получаем пароль нашего пользователя
    }

    @Override
    public String getUsername() {
        return this.person.getUsername(); // тут мы получаем юзернейм пользователя
    }

    @Override
    public boolean isAccountNonExpired() { // здесь мы должны прописать логику, про то, что акк не просрочен
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // здесь мы должны прописать логику, про то, что акк не заблокирован
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // логика, для того, что пароль не просрочен
        return true;
    }

    @Override
    public boolean isEnabled() { // этот аккаунт работает ли, включен ли он
        return true;
    }
}
