package com.rebekahperkins.website.service;

import com.rebekahperkins.website.domain.Role;

public interface RoleService {
  Role findRoleByName(String name);
}
