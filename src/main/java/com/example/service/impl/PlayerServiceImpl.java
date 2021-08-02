package com.example.service.impl;

import com.example.exception.ArgumentNotValidException;
import com.example.exception.DeleteEntityException;
import com.example.exception.InvalidEmailException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.GameAccess;
import com.example.model.Player;
import com.example.repository.GameAccessRepository;
import com.example.repository.PlayerRepository;
import com.example.service.interfaces.GameAccessService;
import com.example.service.interfaces.PlayerService;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;
    private final GameAccessService gameAccessService;
 //   private final GameAccessService gameAccessService;
    private final GameAccessRepository gameAccessRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, MessageSource messageSource,
                             GameAccessService gameAccessService,
                             CustomValidator customValidator,
                            // GameAccessService gameAccessService,
                             GameAccessRepository gameAccessRepository) {
        this.playerRepository = playerRepository;
        this.messageSource = messageSource;
        this.customValidator = customValidator;
        this.gameAccessService = gameAccessService;
     //   this.gameAccessService = gameAccessService;
        this.gameAccessRepository = gameAccessRepository;
    }

    @Override
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findPlayerById(UUID id) {
        if (id == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        UUID[] args = new UUID[]{ id };
        return playerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public Player findPlayerByUserId(UUID user) {
        if (user == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Player player = playerRepository.findPlayerByUserId(user);
        if (player == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        return player;
    }

    @Override
    public Player findGuest(String name) {
        if (name == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Player player = playerRepository.findPlayerByLogin(name);
        if (player == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        return player;
    }

    @Override
    public void deletePlayer(UUID id) {
        try {
            List<GameAccess> gameAccesses=gameAccessRepository.findGameAccessesByPlayerId(id);
           if(gameAccesses==null){
                playerRepository.deleteById(id);
            }
            else {
                gameAccessRepository.findGameAccessesByGameId(id)
                        .stream()
                        .peek(gameAccess -> {
                            gameAccessRepository.deleteById(gameAccess.getId());
                        });
                playerRepository.deleteById(id);
            }
        }
        catch (RuntimeException exception) {
            Object[] args = new Object[]{ id, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale()) };
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player updatePlayer(UUID playerId, Player playerRequest) {
        Map<String, String> propertyViolation = customValidator.validate(playerRequest, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        if (!customValidator.validateByRegexp(playerRequest.getEmail(), "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
            throw new InvalidEmailException(ErrorInfo.INVALID_EMAIL_ERROR,
                    messageSource.getMessage("message.InvalidEmail", null, LocaleContextHolder.getLocale()));
        }
        if (playerId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{ playerId, messageSource.getMessage("entity.Player", null, LocaleContextHolder.getLocale()) };
        return playerRepository.findById(playerId).map(player -> {
            player.setLogin(playerRequest.getLogin());
            player.setEmail(playerRequest.getEmail());
            player.setPhoto(playerRequest.getPhoto());
            return playerRepository.save(player);
        }).orElseThrow(()-> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }
}
