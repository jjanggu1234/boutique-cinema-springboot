package com.cinema.repository;

import com.cinema.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
  // INNER JOIN 사용해서 영화정보와 예매정보 리스트 조회
  //  @Query("select r from Reservation r inner join Movie m on r.movie = m")
  //  List<ReservationDTO> getReservationList();
}
