package layeredarchitecture.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class Jwt {

    private final SecretKey key;

    private final long expiration;

    public Jwt(@Value("${jwt.secret-key}") String secret, @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    /**
     * JWT 생성하여 반환한다.
     *
     * @param id 클라이언트 시스템 ID
     * @return String
     */
    public String generateToken(long id) {
        return Jwts.builder()
                   .setSubject(String.valueOf(id))
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 10 hours expiration
                   .signWith(key, SignatureAlgorithm.HS512)
                   .compact();
    }

    /**
     * JWT 에서 클라이언트 시스템 ID를 추출한다.
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
     * @return boolean
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
