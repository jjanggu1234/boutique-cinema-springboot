package com.cinema.service;

import com.cinema.dto.reservation.ReservationDTO;

public interface ReservationService {
  ReservationDTO register(ReservationDTO reservationDTO) throws Exception;
}
