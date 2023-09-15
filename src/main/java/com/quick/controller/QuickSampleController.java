package com.quick.controller;

import com.quick.model.dto.QuickDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/very_quick")
public class QuickSampleController {

    /**
     * Low Level Design - API endpoints - of this product based on below scenario:
     * Insert data
     * Update data
     * Fetch all data
     * Delete data
     * ---------
     * Design simple 4 services:
     * /very_quick
     *  POST (create data - Non-Idempotent )
     *  PUT (update data)
     *  GET
     *  DELETE
     */

    @PostMapping
    public ResponseEntity<QuickDataDto> createData(QuickDataDto data){
        // Real scenario: service request for creation
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QuickDataDto> updateData(QuickDataDto data){
        // Real scenario: service request to update
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<QuickDataDto> getData(){
        // Real scenario: service request to getData
        return new ResponseEntity<>(new QuickDataDto("sample","quick"), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteData(String name){
        // Real scenario: service request for deletion by name
        // Sanitizing name
        name = name.replaceAll("[^a-zA-Z0-9\\s]", "");
        return new ResponseEntity<>("Successfully deleted : "+ name, HttpStatus.OK);
    }

}
