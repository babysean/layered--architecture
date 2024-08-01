package layeredarchitecture.architecture.infrastructure;

import layeredarchitecture.architecture.domain.ClientSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientSystemRepository extends JpaRepository<ClientSystem, Long> {}
