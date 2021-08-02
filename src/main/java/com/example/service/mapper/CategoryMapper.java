package com.example.service.mapper;

import com.example.dto.CategoryDto;
import com.example.model.Category;
import com.example.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {
    private final QuestionService questionService;

    @Autowired
    public CategoryMapper(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CategoryDto toDto(Category entity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(entity.getId());
        categoryDto.setTitle(entity.getTitle());
        categoryDto.setDescription(entity.getDescription());
        return categoryDto;
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setTitle(dto.getTitle());
        category.setDescription(dto.getDescription());
        return category;
    }

    @Override
    public CategoryDto toShortDto(Category entity) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(entity.getId());
        categoryDto.setTitle(entity.getTitle());
        categoryDto.setDescription(entity.getDescription());
        return categoryDto;
    }
}
