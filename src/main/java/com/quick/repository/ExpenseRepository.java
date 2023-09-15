package com.quick.repository;

import com.quick.model.entity.Category;
import com.quick.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByDescription(String description);
    @Query("SELECT COUNT(*) FROM Expense e WHERE e.category = :category")
    Long countExpenseByCategory(@Param("category") Category category);
}
