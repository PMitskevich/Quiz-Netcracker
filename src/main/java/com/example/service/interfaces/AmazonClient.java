package com.example.service.interfaces;

import com.example.dto.GameDto;
import com.example.dto.PlayerDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public interface AmazonClient {
    String uploadFile(MultipartFile multipartFile);
    File convertMultiPartToFile(MultipartFile file) throws Exception;
    String generateFileName(MultipartFile multiPart);
    void uploadFileTos3bucket(String fileName, File file);
    String deleteFileFromS3Bucket(String fileUrl);
    GameDto putObject(String fileUrl, UUID gameId);
    PlayerDto putObjectForPlayer(String fileUrl, UUID playerId);
}
