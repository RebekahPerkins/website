package com.rebekahperkins.website.service;

import com.rebekahperkins.website.dao.RoleDao;
import com.rebekahperkins.website.dao.UserDao;
import com.rebekahperkins.website.domain.Role;
import com.rebekahperkins.website.domain.User;
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
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User register(User user) {
        user.setRole(roleDao.findByName("ROLE_USER"));

        return userDao.save(user);
    }
}