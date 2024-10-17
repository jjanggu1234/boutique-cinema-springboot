package com.cinema.controller;

import com.cinema.dto.reservation.ReservationDTO;
import com.cinema.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationDTO> createReservation(ReservationDTO reservationDTO)
      throws Exception {
    // 예약 생성
    ReservationDTO createdReservationDTO = reservationService.register(reservationDTO);

    return new ResponseEntity<>(createdReservationDTO, HttpStatus.CREATED);
  }
}
