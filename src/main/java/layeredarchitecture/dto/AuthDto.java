package layeredarchitecture.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "인증 정보")
public class AuthDto {

    @Schema(description = "ID")
    @NotNull
    private long id;

    @Schema(description = "비밀번호")
    @NotNull
    private String password;

}
