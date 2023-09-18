package com.quick.controller;

import com.quick.model.dto.ExpenseDto;
import com.quick.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExpenseControllerTest {

    @InjectMocks
    private ExpenseController controller;

    @Mock
    private ExpenseService expenseService;



    private ExpenseDto expense;
    private Long id;

    @BeforeEach
    void setUp() {
        expense = new ExpenseDto(new Date(), "Patel","Lauki","Vegetables",4,0);
        id = 1L;
    }

    @Test
    void addExpense() {
        Mockito.when(expenseService.add(expense)).thenReturn(expense);
        ResponseEntity<ExpenseDto> response = controller.addExpense(expense);
        assertEquals(response.getBody(), expense);
    }

    @Test
    void updateData() {
        Mockito.when(expenseService.update(id, expense)).thenReturn(expense);
        ResponseEntity<ExpenseDto> response = controller.updateExpense(id, expense);
        assertEquals(response.getBody(), expense);
    }

    @Test
    void getData() {
        Mockito.when(expenseService.findById(id)).thenReturn(expense);
        ResponseEntity<ExpenseDto> response = controller.getExpenseById(id);
        assertEquals(response.getBody(), expense);
    }

}