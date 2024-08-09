package layeredarchitecture.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import layeredarchitecture.architecture.presentation.response.ErrorResponse;
import layeredarchitecture.architecture.presentation.response.FieldErrorDetail;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * HTTP Header 의 MediaType을 application/problem+json 으로 설정한다.
     */
    private HttpHeaders setProblemJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return headers;
    }

    /**
     * CustomException 을 처리하는 핸들러
     *
     * @param e       CustomException
     * @param request HttpServletRequest
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create(e.getErrorCode()
                                                                     .getType()))
                                                   .title(e.getErrorCode()
                                                           .getTitle())
                                                   .status(e.getErrorCode()
                                                            .getStatus()
                                                            .value())
                                                   .detail(e.getErrorCode()
                                                            .getDetail())
                                                   .instance(URI.create(request.getRequestURI()))
                                                   .build();
        return new ResponseEntity<>(errorResponse, setProblemJsonHeaders(), errorResponse.getStatus());
    }

    /**
     * MethodArgumentNotValidException 을 처리하는 핸들러
     *
     * @param e       MethodArgumentNotValidException
     * @param request HttpServletRequest
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldErrorDetail> fieldErrors = e.getBindingResult()
                                              .getFieldErrors()
                                              .stream()
                                              .map(error -> new FieldErrorDetail(error.getField(), error.getDefaultMessage()))
                                              .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create("/errors/parameter"))
                                                   .title("PARAMETER NOT VALID")
                                                   .status(HttpStatus.BAD_REQUEST.value())
                                                   .detail("파라미터가 올바르지 않습니다.")
                                                   .instance(URI.create(request.getRequestURI()))
                                                   .fieldErrors(fieldErrors)
                                                   .build();

        return new ResponseEntity<>(errorResponse, setProblemJsonHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * HttpMessageNotReadableException 을 처리하는 핸들러
     *
     * @param request HttpServletRequest
     * @return ResponseEntity<ErrorResponse>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .type(URI.create("/errors/parameter"))
                                                   .title("PARAMETER NOT VALID")
                                                   .status(HttpStatus.BAD_REQUEST.value())
                                                   .detail("body 의 값을 읽을 수 없습니다.")
                                                   .instance(URI.create(request.getRequestURI()))
                                                   .build();

        return new ResponseEntity<>(errorResponse, setProblemJsonHeaders(), HttpStatus.BAD_REQUEST);
    }

}
