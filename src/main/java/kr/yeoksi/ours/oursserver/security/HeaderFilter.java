package kr.yeoksi.ours.oursserver.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * API Gateway로 부터 오는 요청의 헤더에 X-User-Uid와 X-User-Email이 유효한지 검증하는 필터
 */
public class HeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uid = request.getHeader("X-User-Uid");
        String email = request.getHeader("X-User-Email");

        // uid와 email에 대한 유효성 검사 로직
        if(uid == null || uid.isEmpty() || email == null || email.isEmpty()) {
            System.out.println("uid와 email에 대한 유효성 검사를 통과하지 못했습니다.");
            logger.debug("uid와 email에 대한 유효성 검사를 통과하지 못했습니다.");
        }

        // 유효성 검사가 성공하면 다음 필터로 넘기기
        else filterChain.doFilter(request, response);
    }
}
