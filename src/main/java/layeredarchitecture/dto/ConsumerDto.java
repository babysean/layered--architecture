package layeredarchitecture.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "고객 정보")
public class ConsumerDto {

    @Schema(description = "고객 DI")
    private Long id;

    @Schema(description = "고객 이름")
    private String name;

}
