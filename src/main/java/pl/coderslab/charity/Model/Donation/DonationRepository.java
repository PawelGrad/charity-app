package pl.coderslab.charity.Model.Donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.Model.Donation.DonationEntity;

import java.util.Optional;

public interface DonationRepository  extends JpaRepository<DonationEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT sum(quantity) FROM donations")
    Optional<Long> countBags();

    @Modifying
    @Query(nativeQuery = true, value ="UPDATE donations SET archivised = true where institution_id = ?")
    void archivise(Long id);
}
