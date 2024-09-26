package com.cinema.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${com.cinema.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        File tempFolder = new File(uploadPath);

        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
        uploadPath = tempFolder.getAbsolutePath();
    }

    public String saveFile(MultipartFile file) throws RuntimeException {
        if (file == null || file.isEmpty()) {
            return null; // 파일이 없거나 비어있는 경우 null 반환
        }

        String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path savePath = Paths.get(uploadPath, savedName);

        try {
            Files.copy(file.getInputStream(), savePath);
            String contentType = file.getContentType();

            if (contentType != null && contentType.startsWith("image")) {
                Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);
                Thumbnails.of(savePath.toFile()).size(400, 400).toFile(thumbnailPath.toFile());
            }
            return savedName; // 저장된 파일 이름 반환
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
