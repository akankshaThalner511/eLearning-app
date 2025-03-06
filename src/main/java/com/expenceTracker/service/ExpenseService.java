package com.expenceTracker.service;

import java.util.List;

import com.expenceTracker.DTO.ExpenseDTO;
import com.expenceTracker.DTO.FilterDTO;
import com.expenceTracker.entity.Expense;

public interface ExpenseService {
    Expense findExpenseById(int id);
    void save(ExpenseDTO expenseDTO);
    void update(ExpenseDTO expenseDTO);
    List<Expense> findAllExpenses();
    List<Expense> findAllExpensesByClientId(int id);
    void deleteExpenseById(int id);
    List<Expense> findFilterResult(FilterDTO filter);

}