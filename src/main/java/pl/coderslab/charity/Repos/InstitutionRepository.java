package pl.coderslab.charity.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;

public interface InstitutionRepository  extends JpaRepository<InstitutionEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT count(*) FROM institiutions")
    int countInstitutions();

    @Query(nativeQuery = true, value ="update ins")
    void update();
}
