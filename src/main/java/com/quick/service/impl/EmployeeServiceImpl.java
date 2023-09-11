package com.quick.service.impl;

import com.quick.model.entity.Employee;
import com.quick.model.dto.EmployeeDto;
import com.quick.repository.EmployeeRepository;
import com.quick.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository){
        this.repository = repository;
    }

    @Override
    public List<EmployeeDto> fetchAll() {
        List<Employee> employee = repository.findAll();
        return new ModelMapper().map(employee,new TypeToken<List<EmployeeDto>>(){}.getType());
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        Employee emp = new ModelMapper().map(employeeDto,Employee.class);
        emp = repository.save(emp);
        return new ModelMapper().map(emp,EmployeeDto.class);
    }

}
