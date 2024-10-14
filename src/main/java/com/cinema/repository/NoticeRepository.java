package com.cinema.repository;

import com.cinema.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository  extends JpaRepository<Notice, Long> {

}
