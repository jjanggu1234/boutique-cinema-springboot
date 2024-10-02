package com.cinema.repository;

import com.cinema.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

                                                                // pk타입(mId) String
public interface MemberRepository extends JpaRepository<Member, String> {

}
