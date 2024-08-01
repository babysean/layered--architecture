package layeredarchitecture.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "인증 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    @Schema(description = "시스템 ID")
    private String id;

    @Schema(description = "JWT")
    private String jwt;

    @Schema(description = "JWT 유효성")
    private Boolean isValid;

}
