package layeredarchitecture.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreFruitListDto {

    private Long id;

    private Long storeId;

    private Long fruitId;

    private String fruitName;

    private Long price;

    private Long quantity;

}
