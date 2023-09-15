package com.quick.service;

import com.quick.model.dto.CategoryDto;

import java.util.Set;

public interface CategoryService extends CrudService<CategoryDto, Long> {

    Set<CategoryDto> addAll(Set<CategoryDto> t);

    CategoryDto findByName(String name);
}
