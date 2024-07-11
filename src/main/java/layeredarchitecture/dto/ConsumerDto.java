package layeredarchitecture.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumerDto {

    private Long id;
    
    private String name;

}
