package layeredarchitecture.architecture.infrastructure;

import layeredarchitecture.architecture.domain.ClientSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientSystemRepository extends JpaRepository<ClientSystem, String> {

    @Query("SELECT password FROM ClientSystem WHERE name = :name")
    Optional<String> findPasswordByName(@Param("name") String name);

}
