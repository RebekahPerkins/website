package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity findByUsername(String username);

    UserEntity register(UserEntity user);
}