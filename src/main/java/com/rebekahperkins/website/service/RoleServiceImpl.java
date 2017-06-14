package com.rebekahperkins.website.service;

import com.rebekahperkins.website.dao.RoleDao;
import com.rebekahperkins.website.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDao roleDao;

  @Override
  public Role findRoleByName(String name) {
    return roleDao.findByName(name);
  }
}
