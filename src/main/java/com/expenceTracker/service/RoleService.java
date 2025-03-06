package com.expenceTracker.service;

import com.expenceTracker.entity.Role;

public interface RoleService{
    Role findRoleByName(String name);
}