package com.quick.service.impl;

import com.quick.exception.RequestConflictException;
import com.quick.model.dto.CategoryDto;
import com.quick.model.entity.Category;
import com.quick.repository.CategoryRepository;
import com.quick.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<CategoryDto> addAll(Set<CategoryDto> categoryDtos) {
        Set<CategoryDto> response = new HashSet<>();
        categoryDtos.forEach(categoryDto -> {
            CategoryDto res = add(categoryDto);
            if(res != null){
                response.add(res);
            }else{
                log.warn("Data Insertion Failed For:"+ categoryDto.toString());
            }
        });
        return response;
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = new ModelMapper().map(categoryDto,Category.class);
        Category categoryByName = categoryRepository.findByName(category.getName());
        if(categoryByName == null){
            Category response = categoryRepository.save(category);
            return new ModelMapper().map(response, CategoryDto.class);
        }else{
            throw new RequestConflictException("Category Already Exists");
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
    public CategoryDto findByName(String name) {
        Category category = categoryRepository.findByName(name);
        return new ModelMapper().map(category,CategoryDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()) {
            if(category.get().getExpenses().isEmpty()) {
                categoryRepository.deleteById(id);
            }else{
                log.warn("User tried to delete a category that has expenses. Delete the expenses before deleting:"+ category);
            }
        }else{
            log.info("User tried to delete a category that does not exist: "+ category);
        }
    }


}
