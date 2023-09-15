package com.quick.service;

import com.quick.model.dto.CategoryDto;

import java.util.Optional;

public interface CategoryService extends CrudService<CategoryDto, Long> {

    Optional<CategoryDto> findByName(String name);
}
