package com.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoutiqueCinemaSpringbootApplication {

  public static void main(String[] args) {
    SpringApplication.run(BoutiqueCinemaSpringbootApplication.class, args);
  }
}
