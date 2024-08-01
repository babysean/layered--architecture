package layeredarchitecture.common.exception.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    CONSUMER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 고객 ID 입니다."),
    CLIENT_SYSTEM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 클라이언트 시스템 입니다."),
    JWT_NOT_FOUND(HttpStatus.UNAUTHORIZED, "JWT 이 존재하지 않습니다."),
    JWT_NOT_VALID(HttpStatus.UNAUTHORIZED, "JWT 가 유효하지 않습니다."),
    ID_PASSWORD_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
    // 사용할 HTTP Status 를 아래 추가하여 사용한다.

    private final HttpStatus status;

    private final String message;
}
