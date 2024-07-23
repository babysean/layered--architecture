package layeredarchitecture.presentation.exception.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // 404 NOT_FOUND
    CONSUMER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 고객 ID 입니다.");

    private final HttpStatusCode status;

    private final String message;
}
