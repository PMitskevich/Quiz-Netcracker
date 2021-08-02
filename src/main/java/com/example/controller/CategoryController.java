package com.example.controller;

import com.example.dto.CategoryDto;
import com.example.model.Category;
import com.example.service.interfaces.CategoryService;
import com.example.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<CategoryDto> getCategories() {
        return categoryService.findAllCategory()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getQuestions(@PathVariable UUID categoryId) {
        return mapper.toDto(categoryService.findCategoryById(categoryId));
    }

    @PostMapping("/save")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = mapper.toEntity(categoryDto);
        return mapper.toDto(categoryService.saveCategory(category));
    }

    @PutMapping("/update/{categoryId}")
    public CategoryDto updateCategory(@PathVariable UUID categoryId,
                                      @Valid @RequestBody CategoryDto categoryDto) {
        Category category = mapper.toEntity(categoryDto);
        return mapper.toDto(categoryService.updateCategory(categoryId, category));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
