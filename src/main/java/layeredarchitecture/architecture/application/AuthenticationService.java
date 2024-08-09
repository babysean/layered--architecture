package layeredarchitecture.architecture.application;

import layeredarchitecture.architecture.domain.JsonWebToken;
import layeredarchitecture.architecture.dto.AuthDto;
import layeredarchitecture.architecture.infrastructure.ClientSystemRepository;
import layeredarchitecture.common.constants.ErrorCode;
import layeredarchitecture.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientSystemRepository clientSystemRepository;

    private final JsonWebToken jwt;

    /**
     * 클라이언트 시스템 정보를 전달 받아 JWT 를 생성하고 반환한다.
     *
     * @param authDto 인증할 클라이언트 시스템 정보
     * @return String
     */
    @Transactional(readOnly = true)
    public String generatedJwt(AuthDto authDto) {
        String clientSystemName = authDto.getName();
        String clientSystemPassword = authDto.getPassword();

        String password = clientSystemRepository.findPasswordByName(authDto.getName())
                                                .orElseThrow(() -> new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCHED));

        if (!clientSystemPassword.equals(password)) {
            throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCHED);
        }

        return jwt.generateToken(clientSystemName);
    }

    /**
     * 전달 받은 token의 유효성을 검사하고 반환한다.
     *
     * @param authHeader 인증 관련 HEADER 정보
     */
    @Transactional(readOnly = true)
    public void validatedJwt(String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        jwt.isTokenValid(token);
    }

}
