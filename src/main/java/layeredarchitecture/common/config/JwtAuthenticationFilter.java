package layeredarchitecture.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import layeredarchitecture.architecture.domain.Jwt;
import layeredarchitecture.architecture.presentation.response.ErrorResponse;
import layeredarchitecture.common.exception.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Jwt jwt;

    /**
     * 요청이 들어올 때마다 실행되는 메서드
     * JWT 를 검증하고, 유효한 경우 사용자 정보를 설정한다.
     *
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param filterChain FilterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // "/auth" 경로는 필터를 태우지 않음
            if (request.getRequestURI()
                       .startsWith("/auth")) {
                filterChain.doFilter(request, response);
                return;
            }

            // 요청 Header 에서 Authorization 추출
            String authHeader = request.getHeader("Authorization");

            // Authorization Header 가 "Bearer "로 시작하지 않거나 없으면 에러 반환
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new JwtAuthenticationException("JWT not found");
            }

            // JWT 추출
            String token = authHeader.replace("Bearer ", "");

            // JWT 유효하지 않으면 에러 반환
            if (!jwt.isTokenValid(token)) {
                throw new JwtAuthenticationException("JWT not valid");
            }

            // 클라이언트 시스템 ID 추출
            String id = jwt.extractId(token);

            // 클라이언트 시스템 ID로 인증 객체 생성 (권한은 null)
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(id, null, null);

            // 요청 세부 정보 설정
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 인증 객체를 SecurityContext 에 설정
            SecurityContextHolder.getContext()
                                 .setAuthentication(authentication);

            // 다음 필터 호출
            filterChain.doFilter(request, response);
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            handleException(response, e, request.getRequestURI());
        }
    }

    /**
     * JwtAuthenticationException 에러 Response 메서드
     *
     * @param response   HttpServletResponse
     * @param e          JwtAuthenticationException
     * @param requestURI String
     * @throws IOException
     */
    private void handleException(HttpServletResponse response, JwtAuthenticationException e, String requestURI) throws IOException {
        response.setContentType("application/problem+json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create("/errors/unauthorized"))
                                                   .title("Unauthorized")
                                                   .status(HttpStatus.UNAUTHORIZED.value())
                                                   .detail(e.getMessage())
                                                   .instance(URI.create(requestURI))
                                                   .build();

        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(errorResponse));
        writer.flush();
    }

}
