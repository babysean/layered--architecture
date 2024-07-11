package layeredarchitecture.infrastructure;

import layeredarchitecture.infrastructure.entity.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<ConsumerEntity, Long> {}
