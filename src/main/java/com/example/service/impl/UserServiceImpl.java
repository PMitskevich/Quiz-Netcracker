package com.example.service.impl;

import com.example.exception.ArgumentNotValidException;
import com.example.exception.AuthorizationException;
import com.example.exception.DeleteEntityException;
import com.example.exception.ExistingUserException;
import com.example.exception.InvalidEmailException;
import com.example.exception.InvalidUserActivationException;
import com.example.exception.ResourceNotFoundException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.Player;
import com.example.model.RoleList;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.security.LoginModel;
import com.example.service.interfaces.GameAccessService;
import com.example.service.interfaces.PlayerService;
import com.example.service.interfaces.UserService;
import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameAccessService gameAccessService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PlayerService playerService;
    private final MailSender mailSender;
    private final MessageSource messageSource;
    private final CustomValidator customValidator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           GameAccessService gameAccessService, MessageSource messageSource,
                           MailSender mailSender, PlayerService playerService, CustomValidator customValidator) {
        this.userRepository = userRepository;
        this.gameAccessService = gameAccessService;
        this.playerService = playerService;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.messageSource = messageSource;
        this.mailSender = mailSender;
        this.customValidator = customValidator;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(LoginModel loginModel) {
        Map<String, String> propertyViolation = customValidator.validate(loginModel, Create.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        User user = new User(loginModel.getLogin(),
                loginModel.getEmail(),
                bCryptPasswordEncoder.encode(loginModel.getPassword())
        );
        User userFromDb = userRepository.findByLoginOrEmail(user.getLogin(), user.getEmail());
        if (userFromDb != null) {
            throw new ExistingUserException(ErrorInfo.EXISTING_USER_ERROR,
                    messageSource.getMessage("message.ExistingUserError", null, LocaleContextHolder.getLocale()));
        } else {
            Player player = new Player(user.getEmail(), user.getLogin(), user);
            user.setRole(RoleList.USER);
            user.setActive(false);
            user.setActivationCode(UUID.randomUUID().toString());
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to localhost. Please, visit next link: http://localhost:4200/activate/%s",
                    user.getLogin(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", message);
            userRepository.save(user);
            playerService.savePlayer(player);
            gameAccessService.createGameAccessByPlayer(user.getId());
            return user;
        }
    }

    @Override
    public User updateUser(UUID userId, User userRequest) {
        Map<String, String> propertyViolation = customValidator.validate(userRequest, Update.class);
        if (!propertyViolation.isEmpty()) {
            throw new ArgumentNotValidException(ErrorInfo.ARGUMENT_NOT_VALID, propertyViolation, messageSource);
        }
        if (!customValidator.validateByRegexp(userRequest.getEmail(), "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) {
            throw new InvalidEmailException(ErrorInfo.INVALID_EMAIL_ERROR,
                    messageSource.getMessage("message.InvalidEmail", null, LocaleContextHolder.getLocale()));
        }
        if (userId == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        Object[] args = new Object[]{userId, messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())};
        return userRepository.findById(userId).map(user -> {
            user.setLogin(userRequest.getLogin());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setRole(userRequest.getRole());
            Player player1 = playerService.findPlayerByUserId(userId);
            Player player2 = new Player(user.getLogin(), user.getEmail());
            playerService.updatePlayer(player1.getId(), player2);
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public void deleteUser(UUID userId) {
        try {
            Player player = playerService.findPlayerByUserId(userId);
            playerService.deletePlayer(player.getId());
            userRepository.deleteById(userId);
        } catch (RuntimeException exception) {
            Object[] args = new Object[]{userId, messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())};
            throw new DeleteEntityException(ErrorInfo.DELETE_ENTITY_ERROR,
                    messageSource.getMessage("message.DeleteEntityError", args, LocaleContextHolder.getLocale()));
        }
    }

    @Override
    public User getUserById(UUID userId) {
        Object[] args = new Object[]{userId, messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())};
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                messageSource.getMessage("message.ResourceNotFound", args, LocaleContextHolder.getLocale())));
    }

    @Override
    public User findUserByUsername(String username) {
        if (username == null) {
            throw new ResourceNotFoundException(ErrorInfo.RESOURCE_NOT_FOUND,
                    messageSource.getMessage("message.ResourceNotFound",
                            new Object[]{null, messageSource.getMessage("entity.User", null, LocaleContextHolder.getLocale())}, LocaleContextHolder.getLocale()));
        }
        User user = userRepository.findByLoginOrEmail(username, username);
        if (user == null) {
            throw new AuthorizationException(ErrorInfo.AUTHORIZATION_ERROR,
                    messageSource.getMessage("message.AuthorizationError", null, LocaleContextHolder.getLocale()));
        }
        return user;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findUserByActivationCode(code);
        if (user == null || !user.getActivationCode().equals(code)) {
            throw new InvalidUserActivationException(ErrorInfo.INVALID_USER_ACTIVATION_ERROR,
                    messageSource.getMessage("message.InvalidUserActivationError", null, LocaleContextHolder.getLocale()));
        }
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public User blockUser(UUID userId) {
        if (userId == null) {
            throw new InvalidUserActivationException(ErrorInfo.INVALID_USER_ACTIVATION_ERROR,
                    messageSource.getMessage("message.InvalidUserActivationError", null, LocaleContextHolder.getLocale()));
        }
        User userToBlock = userRepository.findUserById(userId);
        userToBlock.setActive(!userToBlock.isActive());
        return userRepository.save(userToBlock);
    }

}
