package com.expenceTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenceTracker.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}