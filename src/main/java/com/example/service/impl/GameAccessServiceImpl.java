package com.example.service.impl;

import com.example.exception.DeleteEntityException;
import com.example.exception.InvalidActivationCodeException;
import com.example.exception.InvalidGameAccessException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.Game;
import com.example.model.GameAccess;
import com.example.model.Player;
import com.example.repository.GameAccessRepository;
import com.example.repository.GameRepository;
import com.example.repository.PlayerRepository;
import com.example.service.interfaces.GameAccessService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameAccessServiceImpl implements GameAccessService {

    private final GameAccessRepository gameAccessRepository;
   // private final PlayerService playerService;
    private final PlayerRepository playerRepository;
 //   private final GameService gameService;
    private final GameRepository gameRepository;
    private final MailSender mailSender;
    private final MessageSource messageSource;

    public GameAccessServiceImpl(GameAccessRepository gameAccessRepository,// PlayerService playerService,
                                 PlayerRepository playerRepository,
                                 //   GameService gameService,
                                 GameRepository gameRepository, MailSender mailSender, MessageSource messageSource) {
        this.gameAccessRepository = gameAccessRepository;
      //  this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        //    this.gameService = gameService;
        this.mailSender = mailSender;
        this.messageSource = messageSource;
    }

    @Override
    public List<GameAccess> getGameAccessesByGameId(UUID gameId) {
        List<GameAccess> gameAccesses = gameAccessRepository.findGameAccessesByGameId(gameId);
        return gameAccesses;
    }

    @Override
    public List<GameAccess> getGameAccessesByPlayerId(UUID gameId) {
        List<GameAccess> gameAccesses = gameAccessRepository.findGameAccessesByPlayerId(gameId);
        return gameAccesses;
    }

    @Override
    public Game createGameAccessByGame(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Game game = gameRepository.findGameById(id);
        if (game == null) {
            Object[] args = new Object[]{id, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())};
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale()));
        }
        playerRepository.findAll()
                .stream()
                .peek(player -> {
                    GameAccess gameAccess = new GameAccess();
                    gameAccess.setGame(game);
                    gameAccess.setPlayer(player);
                    if (game.getPlayer().getId() == player.getId() || game.getAccess().equals("PUBLIC")) {
                        gameAccess.setAccess(true);
                    } else {
                        gameAccess.setAccess(false);
                    }
                    gameAccess.setActivationCode(String.valueOf((int) (Math.random() * 899999 + 100000)));
                    gameAccessRepository.save(gameAccess);
                });

        return game;
    }

    @Override
    public Player createGameAccessByPlayer(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound", new Object[]{null,
                            messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Player player = playerRepository.findPlayerByUserId(id);
        if (player == null) {
            Object[] args = new Object[]{id, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())};
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale()));
        }
        gameRepository.findAll()
                .stream()
                .peek(game -> {
                    GameAccess gameAccess = new GameAccess();
                    gameAccess.setGame(game);
                    gameAccess.setPlayer(player);
                    if (game.getAccess().equals("PRIVATE")) {
                        gameAccess.setAccess(false);
                    } else {
                        gameAccess.setAccess(true);
                    }
                    gameAccess.setActivationCode(String.valueOf((int) (Math.random() * 899999 + 100000)));
                    gameAccessRepository.save(gameAccess);
                });
        return player;
    }

    @Override
    public GameAccess activateGame(String code) {
        GameAccess gameAccess = gameAccessRepository.findGameAccessesByActivationCode(code);
        if (gameAccess == null) {
            throw new InvalidActivationCodeException(ErrorInfo.INVALID_ACTIVATION_CODE_ERROR,
                    messageSource.getMessage("message.InvalidActivationCode", new Object[]{null}, LocaleContextHolder.getLocale()));
        } else {
            gameAccess.setAccess(true);
            gameAccess.setActivationCode(String.valueOf((int) (Math.random() * 899999 + 100000)));
            gameAccessRepository.save(gameAccess);
            return gameAccess;
        }
    }

    @Override
    public GameAccess deactivateGame(UUID gameId, UUID playerId) {
        GameAccess gameAccess = gameAccessRepository.findGameAccessesByGameIdAndPlayerId(gameId, playerId);
        if (gameAccess == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        if (gameAccess.isAccess()) {
            gameAccess.setAccess(false);
        }
        gameAccessRepository.save(gameAccess);
        return gameAccess;
    }


    @Override
    public String sendActivateCode(UUID gameId, UUID playerId) {
        Game game = gameRepository.findGameById(gameId);
        Player player = playerRepository.findPlayerById(playerId);
        if (game == null || player == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        } else {
            GameAccess gameAccess = gameAccessRepository.findGameAccessesByGameIdAndPlayerId(gameId, playerId);
            String message = String.format(
                    "Hello, %s! \n" +
                            "You accessed the game from the link http://localhost:4200/game/%s. Your activation code: %s",
                    player.getLogin(),
                    gameAccess.getGame().getId().toString().trim(),
                    gameAccess.getActivationCode()
            );
            mailSender.send(player.getEmail(), "Activation game", message);
            return message;
        }
    }

    @Override
    public GameAccess checkAccess(UUID gameId, UUID playerId) {
        if (gameId == null || playerId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        GameAccess gameAccess = gameAccessRepository.findGameAccessesByGameIdAndPlayerId(gameId, playerId);
        if (gameAccess == null) {
            throw new InvalidGameAccessException(ErrorInfo.INVALID_GAME_ACCESS_ERROR,
                    messageSource.getMessage("message.InvalidGameAccessError", null, LocaleContextHolder.getLocale()));
        }
        return gameAccess;
    }

    @Override
    public List<GameAccess> updateGameAccess(Game game) {
        if (game == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        List<GameAccess> gameAccesses = gameAccessRepository.findGameAccessesByGameId(game.getId())
                .stream()
                .peek(gameAccess -> {
                    gameAccess.setGame(game);
                    if (game.getAccess().equals("PUBLIC")) {
                        gameAccess.setAccess(true);
                    } else {
                        gameAccess.setAccess(false);
                    }
                    gameAccess.setPlayer(game.getPlayer());
                    gameAccess.setActivationCode(String.valueOf((int) (Math.random() * 899999 + 100000)));
                    gameAccessRepository.save(gameAccess);//}
                })
                .collect(Collectors.toList());
        return gameAccesses;
    }

    @Override
    public List<Player> getPlayersWithTrueAccess(UUID gameId) {
        List<Player> players = new LinkedList<>();
        List<GameAccess> gameAccesses = gameAccessRepository.findGameAccessesByGameId(gameId)
                .stream()
                .peek(gameAccess -> {
                    if (gameAccess.isAccess()) {
                        Player player = playerRepository.findPlayerById(gameAccess.getPlayer().getId());
                        players.add(player);
                    }
                })
                .collect(Collectors.toList());
        return players;
    }

    @Override
    public List<Player> getPlayersWithFalseAccess(UUID gameId) {
        List<Player> players = new LinkedList<>();
        List<GameAccess> gameAccesses = gameAccessRepository.findGameAccessesByGameId(gameId)
                .stream()
                .peek(gameAccess -> {
                    if (!gameAccess.isAccess()) {
                        Player player = playerRepository.findPlayerById(gameAccess.getPlayer().getId());
                        players.add(player);
                    }
                })
                .collect(Collectors.toList());
        return players;
    }

    @Override
    public void deleteGameAccess(UUID id) {
        try {
            GameAccess gameAccess = gameAccessRepository.findGameAccessById(id);
            gameAccessRepository.deleteById(gameAccess.getId());
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{id, messageSource.getMessage("entity.GameAccess", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }
}
