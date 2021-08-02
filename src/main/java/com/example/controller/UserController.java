package com.example.controller;

import com.example.dto.ActivateCodeDto;
import com.example.dto.UserDto;
import com.example.exception.ArgumentNotValidException;
import com.example.exception.InvalidEmailException;
import com.example.exception.detail.ErrorInfo;
import com.example.model.ResponseUser;
import com.example.model.User;
import com.example.security.LoginModel;
import com.example.service.interfaces.GameService;
import com.example.service.interfaces.UserPageService;
import com.example.service.interfaces.UserService;
import com.example.service.mapper.UserMapper;
import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.example.service.validation.validator.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;
    private final UserPageService userPageService;


    @Autowired
    public UserController(UserService userService, UserMapper mapper,
                          UserPageService userPageService) {
        this.userService = userService;
        this.mapper = mapper;
        this.userPageService = userPageService;
    }

    @GetMapping("/findAllUsers")
    public List<UserDto> getUsers() {
        return userService.findAllUser().stream().map(mapper::toShortDto).collect(Collectors.toList());
    }

    @GetMapping("/checkAllUsers")
    public List<UserDto> checkUsers() {
        return userService.findAllUser().stream().map(mapper::toShortDto).collect(Collectors.toList());
    }

    @GetMapping("/findUser/{userId}")
    public UserDto getUsers(@PathVariable UUID userId) {
        return mapper.toDto(userService.getUserById(userId));
    }

    @PutMapping("/update/{userId}")
    public UserDto updateUser(@PathVariable UUID userId,
                              @RequestBody UserDto userDto) {
        User user = mapper.toEntity(userDto);
        return mapper.toDto(userService.updateUser(userId, user));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public UserDto getOneAccount(@PathVariable String username) {
        return mapper.toDto(userService.findUserByUsername(username));
    }

    @PostMapping("/register")
    public UserDto singUp(@RequestBody LoginModel loginModel) {
        return mapper.toDto(userService.saveUser(loginModel));
    }

    @GetMapping("/activate/{code}")
    public ActivateCodeDto activate(Model model, @PathVariable String code) {
        ActivateCodeDto dto = new ActivateCodeDto();
        dto.setText(userService.activateUser(code) ? "Ваша активация прошла успешно!" : "Код активации неверный!");
        return dto;
    }

    @PostMapping(value = "/block/{userId}")
    public UserDto blockUser(@PathVariable UUID userId) {
        return mapper.toDto(userService.blockUser(userId));
    }

    @GetMapping("/pageable")
    public ResponseUser list(@RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "3") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> pageResult = userPageService.findAll(pageRequest);
        List<UserDto> userDtoList = pageResult.getContent().stream().map(mapper::toShortDto).collect(Collectors.toList());
        return new ResponseUser(userDtoList, pageResult.getTotalPages(),
                pageResult.getNumber(), pageResult.getSize());
    }
}