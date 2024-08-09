package layeredarchitecture.architecture.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JsonWebToken {

    // secret 키
    private final SecretKey key;

    // 만료 시간
    private final long expiration;

    public JsonWebToken(@Value("${jwt.secret-key}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    /**
     * JWT 생성하여 반환한다.
     *
     * @param name 클라이언트 시스템 명
     * @return String
     */
    public String generateToken(String name) {
        return Jwts.builder()
                   .setSubject(name)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(
                           System.currentTimeMillis() + expiration)) // 10 hours expiration
                   .signWith(key, SignatureAlgorithm.HS512)
                   .compact();
    }

    /**
     * JWT 에서 클라이언트 시스템 명을 추출한다.
     *
     * @param token JWT
     * @return String
     */
    public String extractId(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
    }

    /**
     * JWT 유효성을 확인하여 반환한다.
     *
     * @param token JWT
     */
    public void isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.JWT_NOT_VALID);
        }
    }

}
