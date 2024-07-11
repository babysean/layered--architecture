package layeredarchitecture.infrastructure;

import layeredarchitecture.infrastructure.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {}
