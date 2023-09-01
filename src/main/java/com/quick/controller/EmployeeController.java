package com.quick.controller;

import com.quick.model.EmployeeDto;
import com.quick.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;

    }
    @PostMapping
    public ResponseEntity<EmployeeDto> createData(EmployeeDto emp){
        EmployeeDto employeeDto = employeeService.saveEmployee(emp);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getData(){
        List<EmployeeDto> employeeDtoList = employeeService.fetchAll();
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }


}
