package layeredarchitecture.architecture.presentation;

import layeredarchitecture.architecture.application.AuthenticationService;
import layeredarchitecture.architecture.domain.JwtManager;
import layeredarchitecture.architecture.presentation.response.AuthResponse;
import layeredarchitecture.architecture.presentation.response.SuccessResponse;
import layeredarchitecture.common.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtManager jwtManager;

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> generatedJwt(@RequestBody AuthDto authDto) {
        String jwt = this.jwtManager.generateToken(authDto.getId());

        return ResponseEntity.ok(AuthResponse.builder()
                                             .jwt(jwt)
                                             .build());
    }

    @GetMapping("/check")
    public ResponseEntity<AuthResponse> getId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        boolean isValid = jwtManager.isTokenValid(token);
        return ResponseEntity.ok(AuthResponse.builder()
                                             .isValid(isValid)
                                             .build());
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> clientLogin(@RequestHeader("Authorization") String authHeader, @RequestBody AuthDto authDto) {
        authenticationService.authenticationClientSystem(authHeader, authDto);

        return ResponseEntity.ok(SuccessResponse.builder()
                                                .status(200)
                                                .message("Success")
                                                .build());
    }

}
