package com.example.service.impl;

import com.example.exception.ArgumentNotValidException;
import com.example.dto.GameMessage;
import com.example.exception.DeleteEntityException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.Answer;
import com.example.model.Player;
import com.example.model.Question;
import com.example.model.Statistics;
import com.example.repository.AnswerRepository;
import com.example.service.interfaces.AnswerService;
import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import com.example.service.interfaces.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;
    private final PlayerService playerService;
    private final GameRoomService gameRoomService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final StatisticsService statisticsService;
    private final QuestionService questionService;

    public AnswerServiceImpl(AnswerRepository answerRepository, MessageSource messageSource,
                             PlayerService playerService, GameRoomService gameRoomService,
                             SimpMessagingTemplate simpMessagingTemplate, StatisticsService statisticsService,
                             QuestionService questionService, CustomValidator customValidator) {
        this.answerRepository = answerRepository;
        this.messageSource = messageSource;
        this.playerService = playerService;
        this.gameRoomService = gameRoomService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.statisticsService = statisticsService;
        this.questionService = questionService;
        this.customValidator = customValidator;
    }

    @Override
    public Answer createAnswer(Answer answer) {
        Map<String, String> propertyViolation = customValidator.validate(answer, Create.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(UUID id) {
        try {
            answerRepository.deleteById(id);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{id, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public Answer updateAnswer(UUID id, Answer answerReq) {
        Map<String, String> propertyViolation = customValidator.validate(answerReq, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())};
        return answerRepository.findById(id).map(answer -> {
            answer.setTitle(answerReq.getTitle());
            answer.setRight(answerReq.getRight());
            return answerRepository.save(answer);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public Answer getAnswerById(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())};
        return answerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<Answer> getALL() {
        return answerRepository.findAll();
    }

    @Override
    public Answer saveStatisticsAndReturnAnswer(UUID questionId, String answerId, UUID playerId,
                                                UUID gameRoomId, int numberAnswer) throws JsonProcessingException {
        Question question = questionService.getQuestionById(questionId);
        Player player = playerService.findPlayerById(playerId);
        Statistics statistics = new Statistics(player, question);
        if (answerId.equals("null")) {
            statistics.setAnswer(null);
            statisticsService.save(statistics);
//            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
//                    messageSource.getMessage("message.ResourceNotFound",
//                            new Object[]{null, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
            return null;
        }
        Answer answer = answerRepository.findAnswerById(UUID.fromString(answerId));
        if (answer == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Answer", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        GameMessage gameMessage = new GameMessage(answer.getRight(), playerId, numberAnswer);
        ObjectMapper objectMapper = new ObjectMapper();
        for (Player fielder : gameRoomService.findById(gameRoomId).getPlayers()) {
            if (!player.getId().equals(fielder.getId())) {
                simpMessagingTemplate.convertAndSend("/topic/game/" + fielder.getId(), objectMapper.writeValueAsString(gameMessage));
            }
        }
        statistics.setAnswer(answer);
        statisticsService.save(statistics);
        return answer;
    }
}
