package layeredarchitecture.infrastructure;

import layeredarchitecture.domain.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {}
