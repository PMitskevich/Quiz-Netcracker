package com.example.service.mapper;

import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto toDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setLogin(entity.getLogin());
        userDto.setPassword(entity.getPassword());
        userDto.setRole(entity.getRole());
        userDto.setActive(entity.isActive());
        userDto.setActivationCode(entity.getActivationCode());
        return userDto;
    }

    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
     //   user.setActive(dto.isActive());
        return user;
    }

    @Override
    public UserDto toShortDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setLogin(entity.getLogin());
        userDto.setPassword(entity.getPassword());
        userDto.setRole(entity.getRole());
      //  userDto.setActive(entity.isActive());
        return userDto;
    }
}
