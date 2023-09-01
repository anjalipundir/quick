package org.example.service;

import org.example.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> fetchAll();

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

}
