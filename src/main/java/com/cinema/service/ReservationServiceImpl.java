package com.cinema.service;

import com.cinema.domain.Movie;
import com.cinema.domain.Reservation;
import com.cinema.dto.reservation.ReservationDTO;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final ModelMapper modelMapper;
  private final ReservationRepository reservationRepository;
  private final MovieRepository movieRepository;

  @Override
  @Transactional
  public ReservationDTO register(ReservationDTO reservationDTO) throws Exception {
    Movie movie =
        movieRepository
            .findById(reservationDTO.getMovieNum())
            .orElseThrow(() -> new IllegalArgumentException("영화번호가 잘못되었습니다."));

    Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
    reservation.setMovie(movie);

    Reservation savedReservation = reservationRepository.save(reservation);

    return modelMapper.map(savedReservation, ReservationDTO.class);
  }
}
