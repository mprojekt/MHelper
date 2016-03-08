package com.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailAdapter implements UserDetails{
    private Acount acount;

    public UserDetailAdapter(Acount acount) {
        this.acount = acount;
    }
    
    public int getId(){
        return this.acount.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authority = new HashSet<>();
        Role role = acount.getPasswd().getRoleUser(); 
        authority.add(new SimpleGrantedAuthority(role.getName()));        
        return authority;
    }

    @Override
    public String getPassword() {
        return acount.getPasswd().getPassword();
    }

    @Override
    public String getUsername() {
        return acount.getPasswd().getLogin();
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
        return acount.isEnabled();
    }

}
