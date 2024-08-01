package layeredarchitecture.architecture.infrastructure;

import layeredarchitecture.architecture.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {}
