package com.cinema.repository;

import com.cinema.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

                                                                // pk타입(mId) String
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);

    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("select m from Member m where m.id = :id")
    Member getWithRoles(@Param("id") String id);

}


