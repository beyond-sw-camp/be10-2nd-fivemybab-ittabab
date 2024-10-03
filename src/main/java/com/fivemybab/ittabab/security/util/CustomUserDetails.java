package com.fivemybab.ittabab.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CustomUserDetails extends User {

    private Long userId;
    private Long courseId;

    public CustomUserDetails(Long userId,Long courseId, String username, String password, List<GrantedAuthority> grantedAuthorities) {
        super(username, password, grantedAuthorities);
        this.userId = userId;
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }
}
