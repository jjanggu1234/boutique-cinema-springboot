package com.cinema.dto.member;


import com.cinema.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MemberDTO extends User {        // 로그인 DTO
    private static final long serialVersionUID = 1L;

    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String birth;
    private Integer isTreated;
    private List<String> roleNames = new ArrayList<>();

    public MemberDTO(String id, String password, String name, String email, String phone, String birth, Integer isTreated, List<String> roleNames) {
        super(id, password, roleNames.stream().map(str ->
                new SimpleGrantedAuthority("ROLE_" + str)).collect(Collectors.toList()));
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birth = birth;
        this.isTreated = isTreated;
        this.roleNames = roleNames;
    }

    public Map<String, Object> getClaims() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("id", id);
        dataMap.put("password", password);
        dataMap.put("name", name);
        dataMap.put("roleNames", roleNames);
        dataMap.put("email", email);
        dataMap.put("phone", phone);
        dataMap.put("birth", birth);
        dataMap.put("isTreated", isTreated);

        return dataMap;
    }
}
