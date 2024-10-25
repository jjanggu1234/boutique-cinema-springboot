package com.cinema.repository;

import com.cinema.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

                                                                // pk타입(mId) String
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);

    @Query("SELECT m FROM Member m WHERE m.phone = :phone AND m.email = :email")
    Optional<Member> findByPhoneAndEmail(@Param("phone") String phone, @Param("email") String email);

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.id = :id AND m.name = :name AND m.phone = :phone")
    boolean existsByIdAndNameAndPhone(@Param("id") String id, @Param("name") String name, @Param("phone") String phone);

    @Modifying
    @Query("UPDATE Member m SET m.password = :password WHERE m.id = :id")
    void updatePassword(@Param("id") String id, @Param("password") String password);

    @EntityGraph(attributePaths = {"memberRoleList"})
    @Query("select m from Member m where m.id = :id")
    Member getWithRoles(@Param("id") String id);

    @Query("SELECT m FROM Member m WHERE m.name LIKE %:condition% OR m.email LIKE %:condition%")
    Page<Member> findByCondition(@Param("condition") String condition, Pageable pageable);
}


