package com.quick.controller;

import com.quick.model.QuickData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/very_quick")
public class QuickSampleController {

    /**
     * Low Level Design - API endpoints - of this product based on below scenario:
     * Insert data
     * Update data
     * Fetch all data
     * Delete data
     *
     * Design simple 4 services:
     * /very_quick
     *  POST (create data - Non-Idempotent )
     *  PUT (update data)
     *  GET
     *  DELETE
     */

    @PostMapping
    public ResponseEntity<QuickData> createData(QuickData data){
        // Real scenario: service request for creation
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QuickData> updateData(QuickData data){
        // Real scenario: service request to update
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<QuickData> getData(){
        // Real scenario: service request to getData
        return new ResponseEntity<>(new QuickData("sample","quick"), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteData(String name){
        // Real scenario: service request for deletion by name
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
