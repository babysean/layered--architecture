package layeredarchitecture.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessCode {

    JWT_GENERATED_COMPLETE(HttpStatus.OK, "TOKEN 이 발급 되었습니다.");
    // 사용할 HTTP Status 를 아래 추가하여 사용한다.

    private final HttpStatus status;

    private final String message;
}
