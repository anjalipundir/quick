package org.example.service.impl;

import org.example.entity.Employee;
import org.example.model.EmployeeDto;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.example.service.EmployeeTxnService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceTxnImpl implements EmployeeTxnService {

    private EmployeeRepository repository;

    @Autowired
    EmployeeServiceTxnImpl(EmployeeRepository repository){
        this.repository = repository;
    }
    @Override
    public List<EmployeeDto> fetchAll() {
        List<Employee> employee = repository.findAll();
        return new ModelMapper().map(employee,new TypeToken<List<EmployeeDto>>(){}.getType());
    }

    @Override
    @Transactional
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws NullPointerException{
        Employee emp = new ModelMapper().map(employeeDto,Employee.class);
        emp = repository.save(emp);

        // without @Transaction keyword. The above save(emp) would have saved emp data in the database and then this method fails with a NullPointerException.
        // In this scenario, with the use of @Transactional emp data is not stored.
        repository.save(null); // Throws NullPointerException - Intentional
        return new ModelMapper().map(emp,EmployeeDto.class);
    }
}
