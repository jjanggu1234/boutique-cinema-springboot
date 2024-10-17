package com.cinema.controller;

import com.cinema.dto.reservation.ReservationDTO;
import com.cinema.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationDTO> createReservation(ReservationDTO reservationDTO)
      throws Exception {
    System.out.println(reservationDTO);
    ReservationDTO createdReservationDTO = reservationService.register(reservationDTO);

    return new ResponseEntity<>(createdReservationDTO, HttpStatus.CREATED);
  }

  @GetMapping("/list")
  public ResponseEntity<List<ReservationDTO>> getAllReservations() {
    List<ReservationDTO> reservations = reservationService.getAllReservations();
    return new ResponseEntity<>(reservations, HttpStatus.OK);
  }

  @GetMapping("/member/{mId}")
  public ResponseEntity<List<ReservationDTO>> getReservationsByMember(
      @PathVariable("mId") String mId) throws Exception {
    List<ReservationDTO> reservations = reservationService.getReservationsByMember(mId);

    return new ResponseEntity<>(reservations, HttpStatus.OK);
  }
}
