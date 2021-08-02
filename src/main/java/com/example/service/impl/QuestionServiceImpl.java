package com.example.service.impl;

import com.example.exception.ArgumentNotValidException;
import com.example.exception.DeleteEntityException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.Answer;
import com.example.model.Question;
import com.example.repository.QuestionRepository;
import com.example.service.interfaces.QuestionService;
import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, MessageSource messageSource, CustomValidator customValidator) {
        this.questionRepository = questionRepository;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
    }

    @Override
    public List<Question> findAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public Question saveQuestion(Question question) {
        Map<String, String> propertyViolation = customValidator.validate(question, Create.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        Question question1 = questionRepository.save(question);
        setQuestionToAnswers(question1);
        return question1;
    }

    @Override
    public Question updateQuestion(UUID questionId, Question questionRequest) {
        Map<String, String> propertyViolation = customValidator.validate(questionRequest, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        if (questionId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{ questionId, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale()) };
        return questionRepository.findById(questionId).map(question -> {
            question.setTitle(questionRequest.getTitle());
            question.setDescription(questionRequest.getDescription());
            question.setCategory(questionRequest.getCategory());
            question.setLevel(questionRequest.getLevel());
            question.setPhoto(questionRequest.getPhoto());
            if (questionRequest.getAnswersSet() != null) {
                question.getAnswersSet().clear();
                question.getAnswersSet().addAll(questionRequest.getAnswersSet());
            }
            return questionRepository.save(question);
        }).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteQuestion(UUID questionId) {
        if (questionId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        try {
            questionRepository.deleteById(questionId);
        }
        catch (RuntimeException exception) {
            Object[] args = new Object[]{ questionId, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale()) };
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public Question getQuestionById(UUID questionId) {
        if (questionId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{ questionId, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale()) };
        return questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale()))) ;
    }

    @Override
    public List<Question> getQuestionsByGameId(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Question", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        return questionRepository.getQuestionByGameId(id);
    }

    public void setQuestionToAnswers(Question question1) {
        Set<Answer> answersSet = question1.getAnswersSet()
                .stream()
                .peek(answer -> answer.setQuestion(question1))
                .collect(Collectors.toSet());
        question1.getAnswersSet().clear();
        question1.getAnswersSet().addAll(answersSet);
    }

    @Override
    public Question getQuestionByTitle(String title) {
        return questionRepository.findQuestionByTitle(title);
    }
}
