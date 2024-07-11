package layeredarchitecture.infrastructure;

import layeredarchitecture.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {}
