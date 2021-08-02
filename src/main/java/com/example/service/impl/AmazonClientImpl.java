package com.example.service.impl;

//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.dto.GameDto;
import com.example.dto.PlayerDto;
import com.example.model.Player;
import com.example.repository.GameRepository;
import com.example.repository.PlayerRepository;
import com.example.service.interfaces.AmazonClient;
import com.example.service.mapper.GameMapper;
import com.example.service.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.model.Game;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class AmazonClientImpl implements AmazonClient {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

 //   private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    public AmazonClientImpl(GameRepository gameRepository, GameMapper gameMapper, PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @PostConstruct
    private void initializeAmazon() {
//        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
//        this.s3client = new AmazonS3Client(credentials);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @Override
    public String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

    @Override
    public void uploadFileTos3bucket(String fileName, File file) {
//        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
//                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
  //      s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
        return "Successfully deleted";
    }

    @Override
    public GameDto putObject(String fileUrl, UUID gameId) {
        Game game = gameRepository.findGameById(gameId);
        game.setPhoto(fileUrl);
        return gameMapper.toDto(gameRepository.save(game));
    }

    @Override
    public PlayerDto putObjectForPlayer(String fileUrl, UUID playerId) {
        Player player = playerRepository.findPlayerById(playerId);
        player.setPhoto(fileUrl);
        return playerMapper.toDto(playerRepository.save(player));
    }
}
