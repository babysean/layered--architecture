package layeredarchitecture.infrastructure;

import layeredarchitecture.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {}
