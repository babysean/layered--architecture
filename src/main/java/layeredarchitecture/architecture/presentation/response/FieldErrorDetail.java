package layeredarchitecture.architecture.presentation.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "필드 오류 세부 사항")
public class FieldErrorDetail {

    @Schema(description = "필드 이름")
    private String name;

    @Schema(description = "오류 메시지")
    private String reason;

}
