package com.quick.controller;

import com.quick.model.dto.EmployeeDto;
import com.quick.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;

    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new Employee", response = EmployeeDto.class, consumes="application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created") })
    public ResponseEntity<EmployeeDto> createEmployee(@ApiParam(value = "Created employee object" ,required=true )  @Validated @RequestBody EmployeeDto emp){
        EmployeeDto employeeDto = employeeService.saveEmployee(emp);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getData(){
        List<EmployeeDto> employeeDtoList = employeeService.fetchAll();
        return new ResponseEntity<>(employeeDtoList, HttpStatus.OK);
    }


}
