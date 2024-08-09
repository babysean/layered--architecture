package layeredarchitecture.architecture.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "인증 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    @Schema(description = "성공 제목")
    private String title;

    @Schema(description = "HTTP 상태 코드")
    private int status;

    @Schema(description = "성공 설명")
    private final String detail;

}
