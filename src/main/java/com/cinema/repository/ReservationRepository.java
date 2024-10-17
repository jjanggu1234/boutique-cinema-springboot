package com.cinema.repository;

import com.cinema.domain.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
  List<Reservation> findByMemberId(String mId);
}
