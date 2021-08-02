package com.example.service.mapper;

import com.example.dto.GameDto;
import com.example.model.Category;
import com.example.model.Game;
import com.example.model.GameCategory;
import com.example.model.Player;
import com.example.service.interfaces.CategoryService;
import com.example.service.interfaces.GameCategoryService;
import com.example.service.interfaces.PlayerService;
import com.example.service.interfaces.UserService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GameMapper implements Mapper<Game, GameDto> {
    private final UserService userService;
    private final QuestionMapper questionMapper;
    private final PlayerService playerService;
    private final GameCategoryService gameCategoryService;

    public GameMapper(UserService userService, GameCategoryService gameCategoryService,
                      QuestionMapper questionMapper, PlayerService playerService) {
        this.userService = userService;
        this.questionMapper = questionMapper;
        this.playerService = playerService;
        this.gameCategoryService = gameCategoryService;
    }

    @Override
    public GameDto toDto(Game entity) {
        GameDto gameDto = new GameDto();
        gameDto.setId(entity.getId());
        gameDto.setTitle(entity.getTitle());
        gameDto.setAverageRating(entity.getAverageRating());
//        gameDto.setGameCategory(entity.getGameCategory().getId());
        gameDto.setViews(entity.getViews());
        gameDto.setRatingCount(entity.getRatingCount());
        gameDto.setPhoto(entity.getPhoto());
        gameDto.setDescription(entity.getDescription());
        gameDto.setQuestions(entity.getQuestions()
                .stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toSet()));
        gameDto.setPlayer(entity.getPlayer().getId());
        gameDto.setAccess(entity.getAccess());
        return gameDto;
    }

    @Override
    public Game toEntity(GameDto dto) {
        Player player = playerService.findPlayerById(dto.getPlayer());
        Game game = new Game();

//        GameCategory gameCategory = gameCategoryService.findGameCategoryById(dto.getGameCategory());
        game.setId(dto.getId());
        game.setDescription(dto.getDescription());
        game.setTitle(dto.getTitle());

        game.setAverageRating(dto.getAverageRating());
//        game.setGameCategory(gameCategory);
        game.setViews(dto.getViews());
        game.setRatingCount(dto.getRatingCount());

        game.setPhoto(dto.getPhoto());
        game.setQuestions(dto.getQuestions()
                .stream()
                .map(questionMapper::toEntity)
                .collect(Collectors.toSet()));
        game.setPlayer(player);
        game.setAccess(dto.getAccess());
        return game;
    }

    @Override
    public GameDto toShortDto(Game entity) {
        GameDto gameDto = new GameDto();
        gameDto.setId(entity.getId());
        gameDto.setTitle(entity.getTitle());
        gameDto.setDescription(entity.getDescription());
        gameDto.setPhoto(entity.getPhoto());
        gameDto.setPlayer(entity.getPlayer().getId());
        gameDto.setAccess(entity.getAccess());
//        gameDto.setQuestions(entity.getQuestions()
//                .stream()
//                .map(questionMapper::toShortDto)
//                .collect(Collectors.toSet()));
        return gameDto;
    }
}
