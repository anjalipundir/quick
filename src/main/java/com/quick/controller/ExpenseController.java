package com.quick.controller;

import com.quick.model.dto.ExpenseDto;
import com.quick.model.dto.TotalExpenseDto;
import com.quick.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
/*
    * Entry: Add new expenses with fields for date, description, category and amount.
    * List: Display a list of all entered expenses, showing date, description, category, and amount.
    * Total: Get the total expenses for the selected date range.
            * Editing: Edit update expenses.
    * Deletion: Delete expenses entry.

 */
    private final ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<Set<ExpenseDto>> getAllExpenses(){
        Set<ExpenseDto> expenses = expenseService.findAll();
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<TotalExpenseDto> getTotalExpenseValue(){
        TotalExpenseDto totalExpense = expenseService.findTotalExpense();
        if (totalExpense == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(totalExpense, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id){
        ExpenseDto expense = expenseService.findById(id);
        if (expense == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping(path = "/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ExpenseDto>> getExpenseByDescription(@PathVariable String description){
        Set<ExpenseDto> expenses = expenseService.findByDescription(description);
        if (expenses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ExpenseDto>> addExpenses(@RequestBody Set<ExpenseDto> expenses){
        Set<ExpenseDto> response = expenseService.addAll(expenses);
        if(response.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody ExpenseDto expense){
        ExpenseDto response = expenseService.add(expense);
        if(response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExpenseDto> deleteExpense(@PathVariable Long id){
        expenseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
