package layeredarchitecture.application;

import layeredarchitecture.domain.Jwt;
import layeredarchitecture.dto.AuthDto;
import layeredarchitecture.dto.ClientSystemDto;
import layeredarchitecture.infrastructure.ClientSystemRepository;
import layeredarchitecture.presentation.exception.CustomException;
import layeredarchitecture.presentation.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientSystemRepository clientSystemRepository;

    private final Jwt jwt;

    /**
     * 클라이언트 시스템 DB 인증
     *
     * @param authHeader 헤더 정보
     * @param authDto    인증 정보
     */
    @Transactional(readOnly = true)
    public void authenticationClientSystem(String authHeader, AuthDto authDto) {
        String token = authHeader.replace("Bearer ", "");
        boolean isValid = jwt.isTokenValid(token);

        if (!isValid) {
            throw new CustomException(ErrorCode.JWT_NOT_VALID);
        }

        ClientSystemDto clientSystemDto = clientSystemRepository.findById(authDto.getId())
                                                                .map(clientSystemEntity -> ClientSystemDto.builder()
                                                                                                          .id(clientSystemEntity.getId())
                                                                                                          .name(clientSystemEntity.getName())
                                                                                                          .password(clientSystemEntity.getPassword())
                                                                                                          .build())
                                                                .orElseThrow(() -> new CustomException(ErrorCode.CLIENT_SYSTEM_NOT_FOUND));

        if (!authDto.getPassword()
                    .equals(clientSystemDto.getPassword())) {
            throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCHED);
        }
    }

}
