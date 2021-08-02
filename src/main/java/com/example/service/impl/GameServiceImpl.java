package com.example.service.impl;

import com.example.exception.ArgumentNotValidException;
import com.example.exception.DeleteEntityException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.*;
import com.example.repository.GameRepository;
import com.example.service.interfaces.GameAccessService;
import com.example.service.interfaces.GameService;
import com.example.service.interfaces.QuestionService;
import com.example.repository.GameRoomRepository;
import com.example.repository.StatisticsRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.CollectionExpression;
import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final QuestionService questionService;
    private final MessageSource messageSource;
    private final GameAccessService gameAccessService;
    private final CustomValidator customValidator;
    private final EntityManager entityManager;
    private final StatisticsRepository statisticsRepository;
    private final GameRoomRepository gameRoomRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository,
                           QuestionService questionService,
                           MessageSource messageSource,
                           EntityManager entityManager,
                           GameAccessService gameAccessService,
                           CustomValidator customValidator, StatisticsRepository statisticsRepository, GameRoomRepository gameRoomRepository) {
        this.gameAccessService = gameAccessService;
        this.gameRepository = gameRepository;
        this.questionService = questionService;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
        this.entityManager = entityManager;
        this.statisticsRepository = statisticsRepository;
        this.gameRoomRepository = gameRoomRepository;
    }

    @Override
    public Game createGame(Game game) {
        Map<String, String> propertyViolation = customValidator.validate(game, Create.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        Game game1 = gameRepository.save(game);
        Set<Question> questions = game1.getQuestions()
                .stream()
                .peek(question -> {
                    question.setGame(game1);
                    questionService.setQuestionToAnswers(question);
                })
                .collect(Collectors.toSet());
        game1.getQuestions().clear();
        game1.getQuestions().addAll(questions);
        gameAccessService.createGameAccessByGame(game.getId());
        return game1;
    }


    @Override
    public List<Game> findByFilter(GameFilterRequest request) {

        BooleanBuilder predicate = new BooleanBuilder();

        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            predicate.and(QGame.game.title.in(request.getTitle()));
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            predicate.and(QGame.game.description.in(request.getDescription()));
        }
        if (request.getViews() != null) {
            predicate.and(QGame.game.views.in(request.getViews()));
        }
        if (request.getRatingCount() != null) {
            predicate.and(QGame.game.ratingCount.in(request.getRatingCount()));
        }
        if (request.getAverageRating() != null) {
            predicate.and(QGame.game.averageRating.in(request.getAverageRating()));
        }
        if (request.getGameCategory() != null) {
            predicate.and(QGame.game.gameCategory.title.in((CollectionExpression<?, ? extends String>) request.getGameCategory()));
        }

        return (List<Game>) gameRepository.findAll(predicate);
    }

    @Override
    public List<Game> searchGamesByTitle(String title) {
        return gameRepository.findAllByTitleContaining(title);
    }

    @Override
    public List<Game> findGamesByCategory(UUID gameCategoryId) {
        return gameRepository.findAllByGameCategoryId(gameCategoryId);
    }

    @Override
    public List<Game> findAllGamesFilteredByTitle() {
        return gameRepository.findByOrderByTitleAsc();
    }

    @Override
    public List<Game> findTopViewedGames() {
        return gameRepository.findTop3ByOrderByViewsDesc();
    }

    @Override
    public List<Game> findAllGamesFilteredByRating() {
        return gameRepository.findAllByOrderByAverageRating();
    }

    @Override
    public List<Game> findAllGamesFilteredByViews() {
        //return gameRepository.findByOrderByViewsDesc();
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC, "views"));
    }

//    @Override
//    public Game updateGameViews(UUID id, Game game) {
//
//        return gameRepository.findById(id).map(game->{
//            game.
//            return gameRepository.save(game);
//        }
//    }

//    @Override
//    public Game updateGameRating(UUID id, Game game) {
//        Object[] args = new Object[]{ id, messageSource.getMessage("entity.Game", null, LocaleContextHolder.getLocale()) };
//        return gameRepository.findById(id).map(game->{
//            game.setPhoto(game.getPhoto());
//
//            return gameRepository.save(game);
//        }).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
//                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
//
//    }

    @Override
    public List<Game> findGameByPlayerId(UUID playerId) {
        return gameRepository.findGamesByPlayerId(playerId);
    }

    @Override
    public List<Game> findPublicGames() {
        return gameRepository.findGameByAccess("PUBLIC");
    }

    @Override
    public void deleteGame(UUID gameId, UUID playerId) {
        try {
            for (Question q : gameRepository.findGameById(gameId).getQuestions()) {
                List<Statistics> statistics = statisticsRepository.getStatisticsByQuestionId(q.getId());
                if (statistics.size() != 0) {
                    for (Statistics s : statistics) {
                        statisticsRepository.deleteById(s.getId());
                    }
                }
            }
            List<GameRoom> gameRoom = gameRoomRepository.findGameRoomByGameId(gameId);
            if (gameRoom.size() != 0) {
                for(GameRoom gR: gameRoom){
                    gameRoomRepository.deleteById(gR.getId());
                }
            }
            List<GameAccess> gameAccesses = gameAccessService.getGameAccessesByGameId(gameId);
            if (gameAccesses != null) {
                for (GameAccess gameAccess : gameAccesses) {
                    gameAccessService.deleteGameAccess(gameAccess.getId());
                }
            }
            gameRepository.deleteById(gameId);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{gameId, messageSource.getMessage("entity.Game", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }


    @Override
    public Game updateGame(UUID id, Game gameReq) {
        Map<String, String> propertyViolation = customValidator.validate(gameReq, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Game", null, LocaleContextHolder.getLocale())};
        return gameRepository.findById(id).map(game -> {
            game.setTitle(gameReq.getTitle());
            game.setDescription(gameReq.getDescription());

            game.setGameCategory(gameReq.getGameCategory());
            game.setAverageRating(game.getAverageRating());
            game.setRatingCount(gameReq.getRatingCount());
            game.setViews(gameReq.getViews());

            game.setPhoto(gameReq.getPhoto());
            game.setAccess(gameReq.getAccess());
            gameAccessService.updateGameAccess(game);
            if (gameReq.getQuestions() != null) {
                Set<Question> questions = gameReq.getQuestions()
                        .stream()
                        .peek(question -> {
                            question.setGame(game);
                            questionService.setQuestionToAnswers(question);
                        }).collect(Collectors.toSet());
                game.getQuestions().clear();
                game.getQuestions().addAll(questions);
            }
            return gameRepository.save(game);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public Game findGameById(UUID id) {
        Object[] args = new Object[]{id, messageSource.getMessage("entity.Game", null, LocaleContextHolder.getLocale())};
        return gameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public Game findGameByTitle(String title) {
        return gameRepository.findGameByTitle(title);
    }

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }
}