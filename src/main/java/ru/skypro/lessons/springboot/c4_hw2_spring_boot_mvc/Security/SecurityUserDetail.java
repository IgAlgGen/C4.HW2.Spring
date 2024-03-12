package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.entitys.Users;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


public class SecurityUserDetail implements UserDetails {
    private Users user;

    public SecurityUserDetail(Users user) {
        this.user = user;
    }

    @Override
    // Возвращает роли пользователя.
    //Вот тут был самый большой затык. Долго не понимал, что сделать.
    //
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(user)
                .map(Users::getUserRole)
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole()))
                .map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
