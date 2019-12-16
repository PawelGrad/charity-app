package pl.coderslab.charity.Model.Institution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.Model.Institution.InstitutionEntity;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository  extends JpaRepository<InstitutionEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT count(*) FROM institiutions")
    Optional<Long> countInstitutions();

    @Modifying
    @Query(nativeQuery = true, value ="UPDATE institiutions SET archivised = true where id = ?")
    void archivise(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM institiutions where archivised is not true")
    List<InstitutionEntity> findNotArchivized();
}
