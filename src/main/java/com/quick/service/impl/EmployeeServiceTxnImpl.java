package com.quick.service.impl;

import com.quick.model.dto.EmployeeDto;
import com.quick.model.entity.Employee;
import com.quick.repository.EmployeeRepository;
import com.quick.service.EmployeeTxnService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceTxnImpl implements EmployeeTxnService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceTxnImpl(EmployeeRepository repository){
        this.repository = repository;
    }
    @Override
    public List<EmployeeDto> fetchAll() {
        List<Employee> employee = repository.findAll();
        return new ModelMapper().map(employee,new TypeToken<List<EmployeeDto>>(){}.getType());
    }

    @Override
    @Transactional
    @Nullable
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws NullPointerException{
        Employee emp = new ModelMapper().map(employeeDto,Employee.class);
        emp = repository.save(emp);

        // without @Transaction keyword. The above save(emp) would have saved emp data in the database and then this method fails with a NullPointerException.
        // In this scenario, with the use of @Transactional emp data is not stored.
        repository.save(null); // Throws NullPointerException - Intentional
        return new ModelMapper().map(emp,EmployeeDto.class);
    }
}
