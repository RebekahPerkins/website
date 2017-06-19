package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}