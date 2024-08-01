package layeredarchitecture.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "공통 성공 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {

    @Schema(description = "HTTP 상태 코드")
    private int status;

    @Schema(description = "성공 메시지")
    private String message;

}
