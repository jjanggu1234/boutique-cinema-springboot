package com.cinema.controller.python;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ReqController {
  @Autowired private WebClient webClient;

  @PostMapping("/java_service")
  public Mono<DetectionResult> serviceRequest(MultipartFile file) {
    MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
    bodyBuilder.part("file", file.getResource());

    // WebClient를 사용하여 FastAPI로 요청 전송
    return webClient
        .post()
        .uri("/detect")
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
        .retrieve()
        .bodyToMono(DetectionResult.class); // 응답을 DetectionResult 클래스에 매핑
  }
}

// DetectionResult 클래스 정의
class DetectionResult {
  private String image; // base64 인코딩된 이미지
  private List<String> class_names; // 클래스 이름 리스트

  // Getters and Setters
  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public List<String> getClass_names() {
    return class_names;
  }

  public void setClass_names(List<String> class_names) {
    this.class_names = class_names;
  }
}
