package com.quick.service.impl;

import com.quick.exception.RequestConflictException;
import com.quick.model.dto.CategoryDto;
import com.quick.model.entity.Category;
import com.quick.repository.CategoryRepository;
import com.quick.repository.ExpenseRepository;
import com.quick.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ExpenseRepository expenseRepository){
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = new ModelMapper().map(categoryDto,Category.class);
        Optional<Category> categoryByName = categoryRepository.findByNameEqualsIgnoreCase(category.getName());
        if(categoryByName.isEmpty()){
            Category response = categoryRepository.save(category);
            return new ModelMapper().map(response, CategoryDto.class);
        }else{
            throw new RequestConflictException("Category Already Exists");
        }
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category category = new ModelMapper().map(categoryDto,Category.class);
        Optional<Category> categoryById = categoryRepository.findById(id);
        if(categoryById.isPresent()){
            Category updatedCategory = categoryById.get();
            if(category.getName() != null)
                updatedCategory.setName(category.getName());
            if(category.getDescription() != null)
                updatedCategory.setDescription(category.getDescription());
            Category response = categoryRepository.save(updatedCategory);
            return new ModelMapper().map(response, CategoryDto.class);
        }else{
            throw new RequestConflictException("Category not found for Id: "+ id);
        }
    }

    @Override
    public Set<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        Type type =  new TypeToken<Set<Category>>(){}.getType();
        return new ModelMapper().map(categories,type);
    }

    @Override
    public CategoryDto findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return new ModelMapper().map(category,CategoryDto.class);
    }

    @Override
    public Optional<CategoryDto> findByName(String name) {
        Optional<Category> category = categoryRepository.findByNameEqualsIgnoreCase(name);
        Type type = new TypeToken<Optional<CategoryDto>>(){}.getType();
        return new ModelMapper().map(category, type);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        // Category is linked to Expenses thus checking if category exists in expense table
        if (category.isPresent()) {
            Long countOfExpenses = expenseRepository.countExpenseByCategory(category.get());
            if (countOfExpenses == null || countOfExpenses == 0) {
                categoryRepository.deleteById(id);
                return;
            }
            log.info("User tried to delete a category: " + category + ", that is linked to expenses. " +
                    "User must delete or unlink expenses before deleting category.");
            return;
        }
        log.info("User tried to delete a category that does not exist: " + category);

    }


}
