package com.rebekahperkins.website.dao;

import com.rebekahperkins.website.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
  Role findByName(String name);
}
