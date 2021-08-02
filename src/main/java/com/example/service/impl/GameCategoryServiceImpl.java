package com.example.service.impl;

import com.example.exception.ArgumentNotValidException;
import com.example.exception.DeleteEntityException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.GameCategory;
import com.example.repository.GameCategoryRepository;
import com.example.service.interfaces.GameCategoryService;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GameCategoryServiceImpl implements GameCategoryService {

    private final GameCategoryRepository gameCategoryRepository;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;

    @Autowired
    public GameCategoryServiceImpl(GameCategoryRepository gameCategoryRepository, MessageSource messageSource,
                                   CustomValidator customValidator) {
        this.gameCategoryRepository = gameCategoryRepository;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
    }

    @Override
    public GameCategory findGameCategoryById(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameCategory", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{ id, messageSource.getMessage("entity.GameCategory", null, LocaleContextHolder.getLocale()) };
        return gameCategoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<GameCategory> findAllGameCategory() {
        return gameCategoryRepository.findAll();
    }

    @Override
    public GameCategory saveGameCategory(GameCategory category) {
        Map<String, String> propertyViolation = customValidator.validate(category, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        return gameCategoryRepository.save(category);
    }

    @Override
    public GameCategory updateGameCategory(UUID categoryId, GameCategory categoryRequest) {
        Map<String, String> propertyViolation = customValidator.validate(categoryRequest, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        if (categoryId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameCategory", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{ categoryId, messageSource.getMessage("entity.GameCategory", null, LocaleContextHolder.getLocale()) };
        return gameCategoryRepository.findById(categoryId).map(category -> {
            category.setDescription(categoryRequest.getDescription());
            category.setTitle(categoryRequest.getTitle());
            return gameCategoryRepository.save(category);
        }).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteGameCategory(UUID categoryId) {
        try {
            gameCategoryRepository.deleteById(categoryId);
        }
        catch (RuntimeException exception) {
            Object[] args = new Object[]{ categoryId, messageSource.getMessage("entity.GameCategory", null, LocaleContextHolder.getLocale()) };
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }


}

