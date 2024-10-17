package com.cinema.dto.member;

import com.cinema.domain.Member;
import com.cinema.domain.MemberRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberJoinDTO {
    private String id;
    private String password;
    private String email;
    private String name;
    private String birth;
    private String phone;
    private int years14More;
    private int useTermsAgree;
    private int personalInfoAgree;
    private Date joinDate;

}
