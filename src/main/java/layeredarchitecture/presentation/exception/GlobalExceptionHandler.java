package layeredarchitecture.presentation.exception;

import layeredarchitecture.presentation.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        // ResponseStatusException 이 발생했을 때, 전역적으로 ErrorResponse 를 반환하도록 설정
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getReason())
                .build();
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

}

