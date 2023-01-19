package com.example.demo.login.service;

import com.example.demo.login.dto.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    private static Logger logger = LoggerFactory.getLogger(UserDetailsImpl.class);

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        logger.info("Inside UserDetailsImpl constructor",username);
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        logger.info("UserDetailsImpl - build",user);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        logger.info("authorities:::::",authorities);
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("getAuthorities::::",authorities);
        return authorities;
    }

    public Long getId() {
        logger.info("id:::::",id);
        return id;
    }

    public String getEmail() {
        logger.info("email:::::",email);
        return email;
    }

    @Override
    public String getPassword() {
        logger.info("password:::::",password);
        return password;
    }

    @Override
    public String getUsername() {
        logger.info("username:::::",username);
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        logger.info("isAccountNonExpired:::::");
        return true;
//        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        logger.info("isAccountNonLocked:::::");
        return true;
//        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        logger.info("isCredentialsNonExpired:::::");
        return true;
//        return false;
    }

    @Override
    public boolean isEnabled() {
        logger.info("isEnabled:::::");
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

