package layeredarchitecture.architecture.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "클라이언트 시스템 인증 정보")
public class AuthDto {

    @Schema(description = "시스템 ID")
    private Long id;

    @Schema(description = "시스템 명")
    @NotNull
    private String name;

    @Schema(description = "비밀번호")
    @NotNull
    private String password;

}
