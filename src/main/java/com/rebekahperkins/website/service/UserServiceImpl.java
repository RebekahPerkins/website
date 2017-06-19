package com.rebekahperkins.website.service;

import com.rebekahperkins.website.dao.RoleDao;
import com.rebekahperkins.website.dao.UserDao;
import com.rebekahperkins.website.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserEntity register(UserEntity user) {
        user.setRole(roleDao.findByName("ROLE_USER"));

        return userDao.save(user);
    }
}