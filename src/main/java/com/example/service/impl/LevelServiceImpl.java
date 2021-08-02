package com.example.service.impl;

import com.example.exception.DeleteEntityException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.Level;
import com.example.repository.LevelRepository;
import com.example.service.interfaces.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final MessageSource messageSource;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository, MessageSource messageSource) {
        this.levelRepository = levelRepository;
        this.messageSource = messageSource;
    }

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level findLevelById(UUID id) {
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Level", null, LocaleContextHolder.getLocale())};
        return levelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public Level save(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level update(UUID id, Level levelRequest) {
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Level", null, LocaleContextHolder.getLocale())};
        return levelRepository.findById(id).map(level -> {
            level.setTitle(levelRequest.getTitle());
            level.setDescription(levelRequest.getDescription());
            return levelRepository.save(level);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public void delete(UUID id) {
        try {
            levelRepository.deleteById(id);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{id, messageSource.getMessage("entity.Level", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }
}
