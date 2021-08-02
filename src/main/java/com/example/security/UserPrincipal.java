package com.example.security;

import com.example.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User userFacade;

    public UserPrincipal(User userFacade){
        this.userFacade = userFacade;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        this.userFacade.getRoleList().forEach(role ->{
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
            authorityList.add(authority);
        });
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.userFacade.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userFacade.getLogin();
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
        return true;
    }
}
