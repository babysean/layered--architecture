package layeredarchitecture.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "STORE_FRUIT_LIST")
public class StoreFruitListEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity storeEntity;

    @ManyToOne
    @JoinColumn(name = "fruit_id", nullable = false)
    private FruitEntity fruitEntity;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

}
