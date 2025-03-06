package com.expenceTracker.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.expenceTracker.DTO.WebUser;
import com.expenceTracker.entity.User;

public interface UserService extends UserDetailsService {
    User findUserByUserName(String username);

    void save(WebUser webUser);
}