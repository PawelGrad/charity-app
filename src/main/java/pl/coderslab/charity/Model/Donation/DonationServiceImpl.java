package pl.coderslab.charity.Model.Donation;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DonationServiceImpl {

    DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public void newDonation(DonationEntity donation){
        donation.setCreationTime(LocalDateTime.now().plusHours(1L));
        donation.setDelivered(false);
        donation.setArchivised(false);
        donationRepository.save(donation);
    }
    public List<DonationEntity> myDonations(Long id){
        return donationRepository.myDonations(id);
    }

    public Long countBags(){
        return donationRepository.countBags().orElse(1L);
    }

    public List<DonationEntity> findAll(){
        return donationRepository.findAll();
    }

}
