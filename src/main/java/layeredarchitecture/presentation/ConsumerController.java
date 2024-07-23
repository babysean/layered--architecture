package layeredarchitecture.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import layeredarchitecture.application.ConsumerService;
import layeredarchitecture.dto.ConsumerDto;
import layeredarchitecture.presentation.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(
        name = "고객 API",
        description = "고객 API"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;

    @Operation(
            summary = "고객 정보 조회",
            description = "고객 ID로 고객 정보를 조회하여 반환한다."
    )
    @ApiResponses(
            {@ApiResponse(
                    responseCode = "200",
                    description = "고객 정보 조회 성공",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"id\": \"1\", \"name\": \"상훈\"}"),
                            schema = @Schema(implementation = ConsumerDto.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "데이터 없음",
                    content = @Content(
                            examples = @ExampleObject(value = "{\"status\":\"NOT_FOUND\", \"message\": \"존재하지 않는 고객 ID 입니다.\"}"),
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )}
    )
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ConsumerDto> getConsumer(@PathVariable Long id) {
        ConsumerDto consumerDto = consumerService.getConsumerInfo(id);
        return ResponseEntity.ok(consumerDto);
    }

}
