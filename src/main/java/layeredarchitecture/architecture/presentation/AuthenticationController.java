package layeredarchitecture.architecture.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import layeredarchitecture.architecture.application.AuthenticationService;
import layeredarchitecture.architecture.dto.AuthDto;
import layeredarchitecture.architecture.presentation.response.AuthResponse;
import layeredarchitecture.architecture.presentation.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(
        name = "JWT API",
        description = "JWT 관련 API 입니다."
)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * 인증 정보를 전달 받아 JWT 를 생성하고 반환한다.
     *
     * @param authDto 인증 정보
     * @return ResponseEntity<AuthResponse>
     */
    @Operation(
            summary = "JWT 발급",
            description = "JWT 를 발급 받는다."
    )
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "JWT 발급 성공",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"title\":\"JWT 발급 성공\",\"status\":200,\"detail\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJHUkVFTk5FVCIsImlhdCI6MTcyMzA5NDE2MywiZXhwIjoxNzIzMDk3NzYzfQ.3tVkL-8OJsjOncA3IRxisX_ZFbyI1L-WQsxbHmgMLFlUab2b1Ppv6ViMfet60phrHPF-mpDJiOaI8EvzVxiAXQ\"}"),
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ), @ApiResponse(
                    responseCode = "401",
                    description = "아이디 또는 비밀번호 불일치",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"type\":\"/errors/authentication\",\"title\":\"AUTH NOT VALID\",\"status\":401,\"detail\":\"아이디 또는 비밀번호가 일치하지 않습니다.\",\"instance\":\"/auth\"}"),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )}
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> generatedJwt(@RequestBody @Validated AuthDto authDto) {
        String jwt = authenticationService.generatedJwt(authDto);

        return ResponseEntity.ok(AuthResponse.builder()
                                             .title("JWT 발급 성공")
                                             .status(HttpStatus.OK.value())
                                             .detail(jwt)
                                             .build());
    }

    /**
     * 토큰 정보가 유효한지 확인하여 반환한다.
     *
     * @param authHeader 인증 정보
     * @return ResponseEntity<AuthResponse>
     */
    @Operation(
            summary = "JWT 유효성 확인",
            description = "JWT 유효성을 확인한다."
    )
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "JWT 유효",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"title\":\"JWT 유효\",\"status\":200,\"detail\":\"JWT가 유효합니다.\"}"),
                            schema = @Schema(implementation = AuthResponse.class)
                    )
            ), @ApiResponse(
                    responseCode = "401",
                    description = "JWT 미 유효",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"type\":\"/errors/authentication\",\"title\":\"AUTH NOT VALID\",\"status\":401,\"detail\":\"JWT 가 유효하지 않습니다.\",\"instance\":\"/auth/check\"}"),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )}
    )
    @GetMapping(
            value = "/check",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthResponse> validatedJwt(@Parameter(
            name = "Authorization",
            description = "JWT"
    ) @RequestHeader("Authorization") String authHeader) {
        authenticationService.validatedJwt(authHeader);

        return ResponseEntity.ok(AuthResponse.builder()
                                             .title("JWT 유효")
                                             .status(HttpStatus.OK.value())
                                             .detail("JWT가 유효합니다.")
                                             .build());
    }

}
