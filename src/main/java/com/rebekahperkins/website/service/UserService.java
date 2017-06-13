package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}