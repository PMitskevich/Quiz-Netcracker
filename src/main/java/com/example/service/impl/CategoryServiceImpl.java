package com.example.service.impl;

import com.example.exception.DeleteEntityException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, MessageSource messageSource) {
        this.categoryRepository = categoryRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Category findCategoryById(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Category", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Category", null, LocaleContextHolder.getLocale())};
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID categoryId, Category categoryRequest) {
        if (categoryId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Category", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{categoryId, messageSource.getMessage("entity.Category", null, LocaleContextHolder.getLocale())};
        return categoryRepository.findById(categoryId).map(category -> {
            category.setDescription(categoryRequest.getDescription());
            category.setTitle(categoryRequest.getTitle());
            return categoryRepository.save(category);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{categoryId, messageSource.getMessage("entity.Category", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }


}
