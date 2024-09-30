package com.fivemybab.ittabab.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CustomUserDetails extends User {

    private Long userId;

    public CustomUserDetails(Long userId, String username, String password, List<GrantedAuthority> grantedAuthorities) {
        super(username, password, grantedAuthorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
