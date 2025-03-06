package com.expenceTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenceTracker.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
}