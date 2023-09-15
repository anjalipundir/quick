package com.quick.service;

import com.quick.model.dto.ExpenseDto;
import com.quick.model.dto.TotalExpenseDto;

import java.util.Set;

public interface ExpenseService extends CrudService<ExpenseDto,Long> {

    Set<ExpenseDto> addAll(Set<ExpenseDto> expenses);

    TotalExpenseDto findTotalExpense();
    Set<ExpenseDto> findByDescription(String description);
}
