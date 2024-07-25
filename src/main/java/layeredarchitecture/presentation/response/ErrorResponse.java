package layeredarchitecture.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.List;

@Data
@Builder
@Schema(description = "공통 오류 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @Schema(description = "오류 유형의 URI")
    private URI type;

    @Schema(description = "오류 제목")
    private String title;

    @Schema(description = "HTTP 상태 코드")
    private int status;

    @Schema(description = "오류 상세 설명")
    private String detail;

    @Schema(description = "오류가 발생한 인스턴스의 URI")
    private URI instance;

    @Schema(description = "필드 오류 세부 사항")
    private List<FieldErrorDetail> fieldErrors;

}
