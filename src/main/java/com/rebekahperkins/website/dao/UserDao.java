package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    User findByUsername(String username);
}