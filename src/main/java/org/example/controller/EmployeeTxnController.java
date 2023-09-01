package org.example.controller;

import org.example.model.EmployeeDto;
import org.example.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public class EmployeeTxnController {

    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> createData(EmployeeDto emp){
        EmployeeDto employeeDto = employeeService.saveEmployee(emp);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }
}
