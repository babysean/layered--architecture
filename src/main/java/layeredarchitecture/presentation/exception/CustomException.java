package layeredarchitecture.presentation.exception;

import layeredarchitecture.presentation.exception.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
