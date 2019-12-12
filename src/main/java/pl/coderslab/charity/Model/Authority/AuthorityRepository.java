package pl.coderslab.charity.Model.Authority;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.Model.Authority.AuthorityEntity;


public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
