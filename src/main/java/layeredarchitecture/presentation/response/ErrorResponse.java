package layeredarchitecture.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@Builder
@Schema(description = "공통 오류 응답")
public class ErrorResponse {

    @Schema(description = "HTTP 상태 코드")
    private HttpStatusCode status;

    @Schema(description = "오류 메시지")
    private String message;

}

