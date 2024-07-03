package layeredArchitecture.repository;

import layeredArchitecture.repository.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
