package com.example.controller;

import com.example.dto.GameDto;
import com.example.model.Game;
import com.example.model.GameFilterRequest;
import com.example.model.Photo;
import com.example.model.Response;
import com.example.service.interfaces.AmazonClient;
import com.example.service.interfaces.GamePageService;
import com.example.service.interfaces.GameService;
import com.example.service.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/game")
public class GameController {
    public final GameService gameService;
    public final GamePageService gamePageService;
    public final GameMapper mapper;
    private final AmazonClient amazonClient;

    @Autowired
    public GameController(GameService gameService, GamePageService gamePageService, GameMapper mapper,
                          AmazonClient amazonClient) {
        this.gameService = gameService;
        this.gamePageService = gamePageService;
        this.mapper = mapper;
        this.amazonClient = amazonClient;
    }

    @GetMapping("/searchByTitle/{title}")
    public List<GameDto> searchGamesByTitle(@PathVariable String title) {
        return gameService.searchGamesByTitle(title).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/topViewed")
    public List<GameDto> findTopViewedGames() {
        return gameService.findTopViewedGames().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/searchByCategory/{id}")
    public List<GameDto> searchGamesByCategory(@PathVariable UUID id) {
        return gameService.findGamesByCategory(id).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filterByRating")
    public List<GameDto> findAllGamesFilteredByRating() {
        return gameService.findAllGamesFilteredByRating().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filterByName")
    public List<GameDto> findAllGamesFilteredByName() {
        return gameService.findAllGamesFilteredByTitle().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/filterByViews")
    public List<GameDto> findAllGamesFilteredByViews() {
        return gameService.findAllGamesFilteredByViews().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findByFilter")
    public List<GameDto> findAllGamesByFilter(@RequestBody GameFilterRequest request) {
        return gameService.findByFilter(request).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public GameDto getGame(@PathVariable UUID id) {
        return mapper.toDto(gameService.findGameById(id));
    }

    @GetMapping("/short/{id}")
    public GameDto getShortGameById(@PathVariable UUID id) {
        return mapper.toShortDto(gameService.findGameById(id));
    }

    @GetMapping("/pageable")
    public Response list(@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "3") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Game> pageResult = gamePageService.findAll(pageRequest);
        return new Response(pageResult.getTotalPages(),
                pageResult.getNumber(), pageResult.getSize());
    }

    @GetMapping("/getByTitle/{title}")
    public GameDto getGameByTitle(@PathVariable String title) {
        return mapper.toDto(gameService.findGameByTitle(title));
    }

    @GetMapping("/player/{playerId}")
    public List<GameDto> getGamesByPlayerId(@PathVariable UUID playerId) {
        return gameService.findGameByPlayerId(playerId).stream()
                .map(mapper::toShortDto)
                .collect(Collectors.toList());
    }

    @GetMapping()
    public List<GameDto> getGames() {
        return gameService.findAllGames().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/public-game")
    public List<GameDto> getPublicGames() {
        return gameService.findPublicGames().stream()
                .map(mapper::toShortDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public GameDto createGame(@RequestBody GameDto gameDto) {
        Game game = mapper.toEntity(gameDto);
        return mapper.toDto(gameService.createGame(game));
    }

    @PutMapping("/update/{id}")
    public GameDto updateGame(@PathVariable UUID id,
                              @RequestBody GameDto gameDto) {
        Game game = mapper.toEntity(gameDto);
        return mapper.toDto(gameService.updateGame(id, game));
    }

    @DeleteMapping("/delete/{gameId}/{playerId}}")
    public ResponseEntity<?> deleteGame(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        gameService.deleteGame(gameId, playerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uploadFile")
    public Photo uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return new Photo(this.amazonClient.uploadFile(file));
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

    @PostMapping("/updateFile/{gameId}")
    public GameDto updateFile(@RequestPart(value = "url") String fileUrl,
                              @PathVariable UUID gameId) {
        return this.amazonClient.putObject(fileUrl, gameId);
    }
}

