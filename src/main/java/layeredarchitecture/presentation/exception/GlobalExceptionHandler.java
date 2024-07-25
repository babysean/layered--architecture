package layeredarchitecture.presentation.exception;

import jakarta.servlet.http.HttpServletRequest;
import layeredarchitecture.presentation.response.ErrorResponse;
import layeredarchitecture.presentation.response.FieldErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private HttpHeaders setProblemJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return headers;
    }

    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create("/errors/custom"))
                                                   .title("Custom Error")
                                                   .status(e.getErrorCode().getStatus().value())
                                                   .detail(e.getErrorCode().getMessage())
                                                   .instance(URI.create(request.getRequestURI()))
                                                   .build();
        return new ResponseEntity<>(errorResponse, setProblemJsonHeaders(), errorResponse.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldErrorDetail> fieldErrors = e.getBindingResult()
                                              .getFieldErrors()
                                              .stream()
                                              .map(error -> new FieldErrorDetail(error.getField(), error.getDefaultMessage()))
                                              .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create("/errors/validation"))
                                                   .title("Validation Error")
                                                   .status(HttpStatus.BAD_REQUEST.value())
                                                   .detail("Validation failed for one or more fields")
                                                   .instance(URI.create(request.getRequestURI()))
                                                   .fieldErrors(fieldErrors)
                                                   .build();

        return new ResponseEntity<>(errorResponse, setProblemJsonHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create("/errors/bad-request"))
                                                   .title("Malformed JSON Error")
                                                   .status(HttpStatus.BAD_REQUEST.value())
                                                   .detail("Request body is not readable or malformed JSON")
                                                   .instance(URI.create(request.getRequestURI()))
                                                   .build();

        return new ResponseEntity<>(errorResponse, setProblemJsonHeaders(), HttpStatus.BAD_REQUEST);
    }

}
