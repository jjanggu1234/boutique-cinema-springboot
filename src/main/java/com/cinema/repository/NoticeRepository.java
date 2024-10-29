package com.cinema.repository;

import com.cinema.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository  extends JpaRepository<Notice, Long> {


     @Query("SELECT n FROM Notice n WHERE n.nTitle LIKE ?1")
     Page<Notice> findByNTitle(String title, Pageable pageable);

     @Query("SELECT n FROM Notice n ORDER BY n.nNum DESC")
     Page<Notice> findLatestByDate(Pageable pageable);

      @Query("SELECT n FROM Notice n ORDER BY n.nDate ASC")
      Page<Notice> findEarliestByDate(Pageable pageable);
}
