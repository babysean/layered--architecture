package layeredarchitecture.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "고객 정보")
public class ConsumerDto {

    @Schema(description = "ID")
    @NotNull
    private Long id;

    @Schema(description = "이름")
    @Pattern(regexp = "^[가-힣]+$")
    private String name;

    @Schema(description = "나이")
    @Min(1)
    private Long age;

    @Schema(description = "가지고 있는 돈")
    @Min(0)
    private Long money;

}
