package layeredarchitecture.architecture.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "클라이언트 시스템 정보")
public class ClientSystemDto {

    @Schema(description = "ID")
    @NotNull
    private Long id;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "시스템 명")
    private String name;

}
