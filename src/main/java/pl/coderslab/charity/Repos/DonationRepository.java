package pl.coderslab.charity.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.Model.Donation.DonationEntity;

public interface DonationRepository  extends JpaRepository<DonationEntity, Long> {

    @Query(nativeQuery = true, value = "SELECT sum(quantity) FROM donations")
    int countBags();
}
