package com.cinema.controller;

import com.cinema.dto.reservation.ReservationDTO;
import com.cinema.dto.reservation.ReviewDTO;
import com.cinema.repository.ReservationRepository;
import com.cinema.service.ReservationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
  private final ReservationService reservationService;
  private final ReservationRepository reservationRepository;
  private final ModelMapper modelMapper;

  @PostMapping
  public ResponseEntity<ReservationDTO> createReservation(@Valid ReservationDTO reservationDTO)
      throws Exception {
    System.out.println(reservationDTO);
    ReservationDTO createdReservationDTO = reservationService.register(reservationDTO);

    return new ResponseEntity<>(createdReservationDTO, HttpStatus.CREATED);
  }

  @GetMapping("/list")
  public ResponseEntity<List<ReservationDTO>> getAllReservations() {
    List<ReservationDTO> reservations = reservationService.getAllReservations();
    return ResponseEntity.ok(reservations);
  }

  @GetMapping("/member/{mId}")
  public ResponseEntity<List<ReservationDTO>> getReservationsByMember(
      @PathVariable("mId") String mId) throws Exception {
    List<ReservationDTO> reservations = reservationService.getReservationsByMember(mId);

    return ResponseEntity.ok(reservations);
  }

  @GetMapping("/{rNum}")
  public ResponseEntity<ReservationDTO> getReservationByRNum(@PathVariable String rNum)
      throws Exception {
    ReservationDTO reservationDTO = reservationService.getReservationByRNum(rNum);

    return ResponseEntity.ok(reservationDTO);
  }

  @PutMapping("/cancel/{rNum}")
  public ResponseEntity<String> cancelReservation(@PathVariable String rNum) throws Exception {
    reservationService.cancelReservation(rNum);

    return ResponseEntity.ok("Reservation canceled successfully.");
  }

  @PutMapping("/review/{rNum}")
  public ResponseEntity<String> updateReview(
      @PathVariable String rNum, @RequestBody @Valid ReviewDTO reviewDTO) throws Exception {

    reservationService.updateReview(rNum, reviewDTO);
    return ResponseEntity.ok("Reservation review successfully.");
  }
}
