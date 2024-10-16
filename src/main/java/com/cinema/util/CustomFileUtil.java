package com.cinema.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
            tempFolder.mkdirs();   // 폴더 생성
        }
        uploadPath = tempFolder.getAbsolutePath();  //절대 경로 설정
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

    // 업로드된 파일을 GET방식으로 호출해서 볼수있게함
    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        if (!resource.exists()) {
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpg");
        }

        HttpHeaders headers = new HttpHeaders();

        try {
            // getFile()은 파일 종류마다 다르게 HTTP헤더 "Content-Type"값을 생성해야 하기 때문에
            // Files.probeContentType()으로 헤더 메세지 생성
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
