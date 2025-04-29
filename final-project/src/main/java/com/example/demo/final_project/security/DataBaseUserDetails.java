package com.example.demo.final_project.security;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.final_project.model.User;
import com.example.demo.final_project.model.Role;

/**
 * classe per la gestione dei dettagli dell'utente
 * implemento UserDetails per risalire alle informazioni dell'utente loggato,
 * devo implementare concretamente i metodi per far capire a Spring Security i
 * dettagli dell'utente
 */

public class DataBaseUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DataBaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        // Mappiamo i ruoli in GrantedAuthority (prendendo il nome del ruolo) perche
        // spring accetta GrantedAuthority
        // altrimenti non funziona e non accetta Role
        this.authorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
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
