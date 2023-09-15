package com.quick.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private Date date;
    private String store;
    private String description;
    private String category;
    private Integer amount;
    private Integer discount;

}
