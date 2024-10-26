package com.cinema.security.filter;

import com.cinema.dto.member.MemberDTO;
import com.cinema.util.JWTUtil;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2

// 필터를 추가하여 임의의 경로에 접근할 경우 Access Token을 확인

public class JWTCheckFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String authHeaderStr = request.getHeader("Authorization");
    try {
      // Bearer accstoken
      String accessToken = authHeaderStr.substring(7);
      Map<String, Object> claims = JWTUtil.validateToken(accessToken);
      log.info("JWT claims: " + claims);
      //            filterChain.doFilter(request, response);

      String id = (String) claims.get("id");
      String password = (String) claims.get("password");
      String name = (String) claims.get("name");
      String email = (String) claims.get("email");
      String phone = (String) claims.get("phone");
      String birth = (String) claims.get("birth");
      Integer isTreated = (Integer) claims.get("isTreated");
      List<String> roleNames = (List<String>) claims.get("roleNames");

      MemberDTO memberDTO =
          new MemberDTO(id, password, name, email, phone, birth, isTreated, roleNames);

      log.info("--------------");
      log.info(memberDTO);
      log.info(memberDTO.getAuthorities());

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(
              memberDTO.getUsername(), password, memberDTO.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      log.error("JWT Check Error....");
      log.error(e.getMessage());
      Gson gson = new Gson();
      String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
      response.setContentType("application/json");
      PrintWriter printWriter = response.getWriter();
      printWriter.print(msg);
      printWriter.close();
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request)
      throws ServletException { // 상위 클래스에 정의된 메서드가 필터로 체크하지 않는 경로나 메서드를 지정하기 위해 사용

    // Preflight 요청은 체크 안함
    if (request.getMethod().equals("OPTIONS")) {
      return true;
    }

    String path = request.getRequestURI();
    String method = request.getMethod();
    log.info("check uri......" + path);

    if (path.matches("/api/admin/movie/[0-9]+") && method.equals("GET")) {
      return true;
    }

    if (path.matches("/api/reservation/list") && method.equals("GET")) {
      return true;
    }

    if (path.startsWith("/api/admin/notices/list") && method.equals("GET")) {
      return true;
    }

    if (path.matches("/api/admin/notices/[0-9]+") && method.equals("GET")) {
      return true;
    }

    return path.startsWith("/api/admin/notices/")
        || path.startsWith("/api/member/")
        || path.startsWith("/api/admin/movie/list")
        || path.matches("/api/admin/movie/view/.*")
        || path.matches("/java_service");
  }
}
