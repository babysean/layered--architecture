package layeredarchitecture.infrastructure;

import layeredarchitecture.domain.ClientSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientSystemRepository extends JpaRepository<ClientSystem, Long> {}
