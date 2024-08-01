package layeredarchitecture.architecture.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHOPPING_CART")
public class ShoppingCart {

    @Id
    @Column(name = "id")
    private long id;

    @Schema(name = "consumer_id")
    private long consumerId;

    @Schema(name = "store_id")
    private long storeId;

    @Schema(name = "is_purchase_completed")
    private boolean isPurchaseCompleted;

}
