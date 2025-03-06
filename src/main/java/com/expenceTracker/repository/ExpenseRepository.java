package com.expenceTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenceTracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByClientId(int id);
}