package layeredarchitecture.infrastructure;

import layeredarchitecture.domain.entity.StoreFruitListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreFruitListRepository extends JpaRepository<StoreFruitListEntity, Long> {

    List<StoreFruitListEntity> findByStoreEntityId(Long storeId);

    Optional<StoreFruitListEntity> findByStoreEntityIdAndFruitEntityId(Long storeId, Long fruitId);

}
