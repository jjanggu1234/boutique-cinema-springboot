package com.cinema.config;

import com.cinema.dto.member.MemberDTO;

import java.io.PrintWriter;
import java.util.Map;

import com.cinema.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {

        log.info("로그인성공");

        MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
        Map<String, Object> claims = memberDTO.getClaims();

        String accessToken = JWTUtil.generateToken(claims, 10);     // 10분
        String refreshToken = JWTUtil.generateToken(claims, 60 * 24);   // 24시간

        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);

        Gson gson =  new Gson();
        String jsonStr = gson.toJson(claims);

        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonStr);
        printWriter.close();


    }
}
