package pl.coderslab.charity.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.Model.Donation.DonationEntity;

public interface DonationRepository  extends JpaRepository<DonationEntity, Long> {
}
