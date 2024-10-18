package com.cinema.service;

import com.cinema.dto.reservation.ReservationDTO;
import java.util.List;

public interface ReservationService {
  ReservationDTO register(ReservationDTO reservationDTO) throws Exception;

  List<ReservationDTO> getAllReservations();

  List<ReservationDTO> getReservationsByMember(String mId) throws Exception;
}
