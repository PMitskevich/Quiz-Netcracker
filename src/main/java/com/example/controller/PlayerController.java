package com.example.controller;

import com.example.dto.PlayerDto;
import com.example.model.Photo;
import com.example.model.Player;
import com.example.service.interfaces.AmazonClient;
import com.example.service.interfaces.GameService;
import com.example.service.interfaces.PlayerService;
import com.example.service.mapper.PlayerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerMapper mapper;
    private final AmazonClient amazonClient;
    private final GameService gameService;

    public PlayerController(PlayerService playerService, PlayerMapper mapper,
                            AmazonClient amazonClient, GameService gameService) {
        this.playerService = playerService;
        this.mapper = mapper;
        this.amazonClient = amazonClient;
        this.gameService = gameService;
    }

    @GetMapping("/id/{id}")
    public PlayerDto getPlayerById(@PathVariable UUID id) {
        return mapper.toDto(playerService.findPlayerById(id));
    }

    @GetMapping("/userId/{id}")
    public PlayerDto getPlayerByUserId(@PathVariable UUID id) {
        return mapper.toDto(playerService.findPlayerByUserId(id));
    }

    @GetMapping("/guest/{name}")
    public PlayerDto getGuest(@PathVariable String name) throws InterruptedException {
        Thread.sleep(200);
        return mapper.toShortDto(this.playerService.findGuest(name));
    }

    @GetMapping()
    public List<PlayerDto> getAllPlayers() {
        return playerService.findAllPlayers().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/update/{playerId}")
    public PlayerDto updatePlayer(@PathVariable UUID playerId, @RequestBody PlayerDto playerDto) {
        Player player = mapper.toEntity(playerDto);
        return mapper.toDto(playerService.updatePlayer(playerId, player));
    }

    @PostMapping("/uploadFile")
    public Photo uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return new Photo(this.amazonClient.uploadFile(file));
    }

    @PostMapping("/register-guest")
    Player singUpGuest(@RequestBody String login) {
        return playerService.savePlayer(new Player(login));
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable UUID id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteGame/{gameId}/{playerId}")
    public ResponseEntity<?> deleteGame(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        gameService.deleteGame(gameId, playerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateFile/{gameId}")
    public PlayerDto updateFile(@RequestPart(value = "url") String fileUrl,
                                @PathVariable UUID gameId) {
        return this.amazonClient.putObjectForPlayer(fileUrl, gameId);
    }

}