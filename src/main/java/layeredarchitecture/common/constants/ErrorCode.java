package layeredarchitecture.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    CLIENT_SYSTEM_NOT_FOUND("/errors/not-found", "DATA NOT FOUND", HttpStatus.NOT_FOUND, "존재하지 않는 클라이언트 시스템 입니다."),
    JWT_NOT_VALID("/errors/authentication", "AUTH NOT VALID", HttpStatus.UNAUTHORIZED, "JWT 가 유효하지 않습니다."),
    ID_PASSWORD_NOT_MATCHED("/errors/authentication", "AUTH NOT VALID", HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
    // 사용할 HTTP Status 를 아래 추가하여 사용한다.

    private final String type;

    private final String title;

    private final HttpStatus status;

    private final String detail;
}
