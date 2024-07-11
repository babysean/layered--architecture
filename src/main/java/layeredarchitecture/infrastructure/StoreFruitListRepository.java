package layeredarchitecture.infrastructure;

import java.util.List;
import java.util.Optional;
import layeredarchitecture.domain.StoreFruitList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreFruitListRepository extends JpaRepository<StoreFruitList, Long> {

    List<StoreFruitList> findByStoreId(Long storeId);

    Optional<StoreFruitList> findByStoreIdAndFruitId(Long storeId, Long fruitId);
}
