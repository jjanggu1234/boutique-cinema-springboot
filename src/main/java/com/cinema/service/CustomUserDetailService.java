package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.dto.member.MemberDTO;
import com.cinema.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.getWithRoles(username);
        if (member == null) {
            throw new UsernameNotFoundException("Not Found");
        }

        MemberDTO memberDTO = new MemberDTO(
                member.getId(),
                member.getPassword(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getBirth(),
                member.getIsTreated(),
                member.getMemberRoleList().stream().map(memberRole ->
                        memberRole.name()).collect(Collectors.toList()));

        log.info(memberDTO);
        return memberDTO;
    }

}
