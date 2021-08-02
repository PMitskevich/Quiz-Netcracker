package com.example.service.mapper;

import com.example.dto.PlayerDto;
import com.example.model.Player;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements Mapper<Player, PlayerDto> {
    private final UserRepository userRepository;

    @Autowired
    public PlayerMapper( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PlayerDto toDto(Player entity) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(entity.getId());
        playerDto.setLogin(entity.getLogin());
        if(entity.getUser()==null){
            return playerDto;
        }
        playerDto.setEmail(entity.getEmail());
        playerDto.setPhoto(entity.getPhoto());
        playerDto.setUser(entity.getUser().getId());
        return playerDto;
    }

    @Override
    public Player toEntity(PlayerDto dto) {
        Player player = new Player();
        User user = userRepository.findUserById(dto.getUser());
        player.setId(dto.getId());
        player.setLogin(dto.getLogin());
        player.setEmail(dto.getEmail());
        player.setPhoto(dto.getPhoto());
        player.setUser(user);
        return player;
    }

    @Override
    public PlayerDto toShortDto(Player entity) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(entity.getId());
        playerDto.setLogin(entity.getLogin());
        return playerDto;
    }
}
