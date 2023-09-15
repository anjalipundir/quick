package com.quick.service.impl;

import com.quick.model.dto.ExpenseDto;
import com.quick.model.dto.TotalExpenseDto;
import com.quick.model.entity.Category;
import com.quick.model.entity.Expense;
import com.quick.repository.CategoryRepository;
import com.quick.repository.ExpenseRepository;
import com.quick.service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository){
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Set<ExpenseDto> addAll(Set<ExpenseDto> expenses) {
        Set<ExpenseDto> response = new HashSet<>();
        for(ExpenseDto expense: expenses){
            ExpenseDto dbExpenseDto = add(expense);
            response.add(dbExpenseDto);
        }
        return response;
    }

    @Override
    @Transactional
    public ExpenseDto add(ExpenseDto expenseDto) {
        Expense expense = new ModelMapper().map(expenseDto, Expense.class);
        addCategoryInfo(expenseDto.getCategory(), expense);
        Expense res = expenseRepository.save(expense);
        return new ModelMapper().map(res,ExpenseDto.class);
    }

    @Override
    public TotalExpenseDto findTotalExpense() {
        long count = expenseRepository.count();
        return new TotalExpenseDto(count);
    }

    @Transactional
    private void addCategoryInfo(String category, Expense expense) {
        if(!category.isBlank()) {
            Category dbCategory = categoryRepository.findByName(category);
            expense.setCategory(dbCategory);
            Set<Expense> expenses = dbCategory.getExpenses();
            expenses.add(expense);

            expense.getCategory().setExpenses(expenses);
        }
    }

    @Override
    public Set<ExpenseDto> findAll() {
        List<Expense> expenses = expenseRepository.findAll();
        return mapToExpenseDto(expenses);
    }

    @Override
    public ExpenseDto findById(Long id) {
        Optional<Expense> response = expenseRepository.findById(id);
        return new ModelMapper().map(response, ExpenseDto.class);
    }

    @Override
    public Set<ExpenseDto> findByDescription(String description) {
        List<Expense> expenses = expenseRepository.findByDescription(description);
        return mapToExpenseDto(expenses);
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

    private Set<ExpenseDto> mapToExpenseDto(List<Expense> expenses){
        Type type =  new TypeToken<Set<ExpenseDto>>(){}.getType();
        return new ModelMapper().map(expenses,type);
    }

}
