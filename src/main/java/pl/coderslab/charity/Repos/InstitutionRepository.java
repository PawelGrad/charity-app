package pl.coderslab.charity.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;

public interface InstitutionRepository  extends JpaRepository<InstitutionEntity, Long> {
}
