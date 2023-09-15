package com.quick.model.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
public class ExpenseDto {

    private Date date;
    private String store;
    @NonNull
    private String description;
    private String category;
    @NonNull
    private int amount;
    private int discount;

}
