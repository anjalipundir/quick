package com.quick.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expense")
public class Expense {
    //Add new expenses with fields for date, description, category and amount.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseId;
    private Date date;
    private String store;
    private String description;

    @ManyToOne
    @JoinColumn(name = "categoryId") // foreign key column in the expense table
    private Category category;
    private int amount;
    private int discount;

}
