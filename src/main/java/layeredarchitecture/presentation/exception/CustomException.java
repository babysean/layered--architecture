package layeredarchitecture.presentation.exception;

import layeredarchitecture.presentation.exception.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
