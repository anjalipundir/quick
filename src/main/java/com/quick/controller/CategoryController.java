package com.quick.controller;

import com.quick.model.dto.CategoryDto;
import com.quick.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "Search all categories", response = CategoryDto.class, responseContainer = "Set", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping
    public ResponseEntity<Set<CategoryDto>> getAllCategories(){
        Set<CategoryDto> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(value = "Search category by id", response = CategoryDto.class, produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        CategoryDto category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Search Categories By Name", response = CategoryDto.class, responseContainer = "Set", produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping(path = "/{description}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getCategoryByName(@ApiParam(value = "name" , required=true) @PathVariable String name){
        CategoryDto category = categoryService.findByName(name);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new Categories", response = CategoryDto.class, responseContainer = "Set", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CategoryDto>> addCategories(@RequestBody Set<CategoryDto> categories){
        Set<CategoryDto> response = categoryService.addAll(categories);
        if(response.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new Category", response = CategoryDto.class, responseContainer = "Set", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created|OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping(path = "/category", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto category){
        CategoryDto response = categoryService.add(category);
        if(response == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Category By Id")
    @DeleteMapping("/{id}")
    public void deleteCategory(@ApiParam(value = "id" , required=true) @PathVariable Long id){
        categoryService.deleteById(id);
    }
}