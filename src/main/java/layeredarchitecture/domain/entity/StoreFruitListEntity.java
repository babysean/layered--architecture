package layeredarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STORE_FRUIT_LIST")
public class StoreFruitListEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "store_id",
            nullable = false
    )
    private StoreEntity storeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "fruit_id",
            nullable = false
    )
    private FruitEntity fruitEntity;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

}
