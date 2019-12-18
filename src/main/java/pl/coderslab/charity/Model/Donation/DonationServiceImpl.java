package pl.coderslab.charity.Model.Donation;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.Model.UserEntity.UserServiceImp;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DonationServiceImpl {

    private DonationRepository donationRepository;


    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public void newDonation(DonationEntity donation){
        donation.setCreationTime(LocalDateTime.now());
        donation.setDelivered(false);
        donation.setArchivised(false);
        donationRepository.save(donation);
    }

    public void flipDelivered(DonationEntity donation){
        if(donation.getDelivered()) {
            donation.setDelivered(false);
            donation.setDeliveredTime(null);
        } else {
            donation.setDelivered(true);
            donation.setDeliveredTime(LocalDateTime.now());
        }
        donationRepository.save(donation);

    }
    public DonationEntity findById(Long id){
        return donationRepository.findById(id).orElse(null);
    }
    public List<DonationEntity> myDonations(Long id){
        return donationRepository.myDonations(id);
    }
    public Long countBags(){
        return donationRepository.countBags().orElse(0L);
    }
    public List<DonationEntity> findAll(){
        return donationRepository.findAll();
    }
    public void save(DonationEntity donation) { donationRepository.save(donation); }

}
