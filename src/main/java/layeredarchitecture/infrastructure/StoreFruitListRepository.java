package layeredarchitecture.infrastructure;

import layeredarchitecture.domain.entity.StoreFruitList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreFruitListRepository extends JpaRepository<StoreFruitList, Long> {

    List<StoreFruitList> findByStoreId(Long storeId);

    Optional<StoreFruitList> findByStoreIdAndFruitId(Long storeId, Long fruitId);

}
