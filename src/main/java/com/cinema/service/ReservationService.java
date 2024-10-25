package com.cinema.service;

import com.cinema.dto.reservation.ReservationDTO;
import com.cinema.dto.reservation.ReviewDTO;
import java.util.List;

public interface ReservationService {
  ReservationDTO register(ReservationDTO reservationDTO) throws Exception;

  List<ReservationDTO> getAllReservations();

  List<ReservationDTO> getReservationsByMember(String mId) throws Exception;

  ReservationDTO getReservationByRNum(String rNum) throws Exception;

  void deleteReservationByRNum(String rNum) throws Exception;

  void cancelReservation(String rNum) throws Exception;

  void updateReview(String rNum, ReviewDTO reviewDTO) throws Exception;
}
