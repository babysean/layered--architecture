package layeredarchitecture.presentation;

import layeredarchitecture.application.AuthenticationService;
import layeredarchitecture.domain.Jwt;
import layeredarchitecture.dto.AuthDto;
import layeredarchitecture.presentation.response.AuthResponse;
import layeredarchitecture.presentation.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Jwt jwt;

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> generatedJwt(@RequestBody AuthDto authDto) {
        String jwt = this.jwt.generateToken(authDto.getId());

        return ResponseEntity.ok(AuthResponse.builder()
                                             .jwt(jwt)
                                             .build());
    }

    @GetMapping("/check")
    public ResponseEntity<AuthResponse> getId(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        boolean isValid = jwt.isTokenValid(token);
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
