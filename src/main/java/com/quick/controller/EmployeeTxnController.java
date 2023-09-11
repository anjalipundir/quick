package com.quick.controller;

import com.quick.model.dto.EmployeeDto;
import com.quick.service.EmployeeTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/txn")
public class EmployeeTxnController {

    private final EmployeeTxnService employeeTxnService;

    @Autowired
    public EmployeeTxnController(EmployeeTxnService employeeService){
        this.employeeTxnService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createData(EmployeeDto emp){
        EmployeeDto employeeDto = employeeTxnService.saveEmployee(emp);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }
}
