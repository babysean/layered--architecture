package layeredarchitecture.presentation.exception;

import layeredarchitecture.presentation.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ex.getErrorCode()
                        .getStatus())
                .message(ex.getErrorCode()
                        .getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}

