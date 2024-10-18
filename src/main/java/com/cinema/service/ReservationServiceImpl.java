package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.domain.Movie;
import com.cinema.domain.Reservation;
import com.cinema.dto.reservation.ReservationDTO;
import com.cinema.repository.MemberRepository;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final ModelMapper modelMapper;
  private final ReservationRepository reservationRepository;
  private final MemberRepository memberRepository;
  private final MovieRepository movieRepository;

  @Override
  @Transactional
  public ReservationDTO register(ReservationDTO reservationDTO) throws Exception {
    Member member =
        memberRepository
            .findById(reservationDTO.getMId())
            .orElseThrow(() -> new IllegalArgumentException("회원 아이디가 잘못되었습니다."));

    Movie movie =
        movieRepository
            .findById(reservationDTO.getMovieNum())
            .orElseThrow(() -> new IllegalArgumentException("영화번호가 잘못되었습니다."));

    Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
    reservation.setMember(member);
    reservation.setMovie(movie);

    Reservation savedReservation = reservationRepository.save(reservation);

    ReservationDTO savedDTO = modelMapper.map(savedReservation, ReservationDTO.class);
    savedDTO.setMId(savedReservation.getMember().getId());
    savedDTO.setMovieNum(savedReservation.getMovie().getMovieNum());
    savedDTO.setRegDate(savedReservation.getRegDate());

    return savedDTO;
  }

  @Override
  public List<ReservationDTO> getAllReservations() {
    List<Reservation> reservations = reservationRepository.findAll();

    return reservations.stream()
        .map(
            reservation -> {
              ReservationDTO savedDTO = modelMapper.map(reservation, ReservationDTO.class);
              savedDTO.setMId(reservation.getMember().getId());
              savedDTO.setMovieNum(reservation.getMovie().getMovieNum());
              return savedDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public List<ReservationDTO> getReservationsByMember(String mId) throws Exception {
    List<Reservation> reservations = reservationRepository.findByMemberId(mId);

    return reservations.stream()
        .map(
            reservation -> {
              ReservationDTO savedDTO = modelMapper.map(reservation, ReservationDTO.class);
              savedDTO.setMId(reservation.getMember().getId());
              savedDTO.setMovieNum(reservation.getMovie().getMovieNum());
              return savedDTO;
            })
        .collect(Collectors.toList());
  }
}
