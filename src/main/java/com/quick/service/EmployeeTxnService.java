package com.quick.service;

import com.quick.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeTxnService {

    List<EmployeeDto> fetchAll();

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

}
