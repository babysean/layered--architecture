package layeredarchitecture.architecture.infrastructure;

import layeredarchitecture.architecture.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {}
